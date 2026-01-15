from z3 import *

answer = 0

with open("AdventOfCode/2025/day10p2/src/main/resources/input.txt", "r") as file:
    for line in file:
        opt = Optimize()
        arr = line.split()[1:]
        result = arr[-1][1:-1].split(",")
        arr = arr[:-1]
        # print(arr)
        # print(result)
        x = [Int(f"x{i}") for i in range(len(arr))]
        for i in range(len(result)):
            row = []
            for ii in range(len(arr)):
                if str(i) in arr[ii]:
                    row.append(x[ii])
            opt.add(sum(row) == result[i])
            # print(row)
        for a in x:
            opt.add(a >= 0)
        opt.minimize(sum(x))
        opt.check()
        # print(opt.model())
        m = opt.model()
        total = sum([m[xi].as_long() for xi in x])
        answer = answer + total

print(answer)
        
            



