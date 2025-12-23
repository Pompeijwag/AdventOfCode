import java.nio.file.*;
import java.util.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;

public class XmlObjectGroupingWithImages {
    public static void main(String[] args) {
        String folderPath = "..\\lib\\input";
        String outputFolder = "..\\lib\\output\\ants";
        String targetName = "ant"; // object name to count

        List<Integer> objectCounts = new ArrayList<>();
        List<Path> xmlFiles = new ArrayList<>();

        try {
            xmlFiles = Files.list(Paths.get(folderPath))
                            .filter(Files::isRegularFile)
                            .filter(p -> p.toString().endsWith(".xml"))
                            .sorted()
                            .toList();

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            for (Path xmlFile : xmlFiles) {
                Document doc = builder.parse(xmlFile.toFile());
                doc.getDocumentElement().normalize();

                NodeList objects = doc.getElementsByTagName("object");
                int count = 0;

                for (int i = 0; i < objects.getLength(); i++) {
                    Element objElement = (Element) objects.item(i);
                    String name = objElement.getElementsByTagName("name")
                                            .item(0)
                                            .getTextContent();
                    if (targetName.equalsIgnoreCase(name)) {
                        count++;
                    }
                }
                objectCounts.add(count);
            }

            // --- Grouping logic ---
            List<Integer> nonZeroCounts = objectCounts.stream()
                                                      .filter(c -> c > 0)
                                                      .sorted()
                                                      .toList();

            if (!nonZeroCounts.isEmpty()) {
                int n = nonZeroCounts.size();
                int p33 = nonZeroCounts.get(n / 3);
                int p66 = nonZeroCounts.get(2 * n / 3);

                for (int i = 0; i < objectCounts.size(); i++) {
                    int count = objectCounts.get(i);
                    Path xmlFile = xmlFiles.get(i);

                    // Find corresponding JPEG
                    String baseName = xmlFile.getFileName().toString().replace(".xml", "");
                    Path jpegFile = Paths.get(folderPath, baseName + ".jpeg");

                    // Decide group folder
                    String groupName;
                    if (count == 0) {
                        groupName = "Group0";
                    } else if (count <= p33) {
                        groupName = "Group1";
                    } else if (count <= p66) {
                        groupName = "Group2";
                    } else {
                        groupName = "Group3";
                    }

                    Path targetDir = Paths.get(outputFolder, groupName);
                    Files.createDirectories(targetDir);

                    // Copy JPEG
                    if (Files.exists(jpegFile)) {
                        Path targetFile = targetDir.resolve(jpegFile.getFileName());
                        Files.copy(jpegFile, targetFile, StandardCopyOption.REPLACE_EXISTING);
                    }
                }
            }

            System.out.println("JPEGs copied into group folders successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
