import numpy as np
import matplotlib.pyplot as plt

with open("out.txt") as f:
    data = f.read()

    data = data.split('\n')

    y = [int(row.split(' ')[0])/1000000 for row in data]
    x = [int(row.split(' ')[1]) for row in data]

    fig = plt.figure()

    ax1 = fig.add_subplot(111)

    ax1.set_title("Graph showing relationship between numbers and the time required to calculate them")    
    ax1.set_xlabel('Number')
    ax1.set_ylabel('Time required to calculate number')
    ax1.set_yscale('log')
    slope = np.polyfit(np.array(x), np.array(y), 1)
    print(np.log(slope))
    print(type(x[0]))
    print(x)
    ax1.plot(x,y, c='r', label='the data')

    leg = ax1.legend()

    plt.show()