import numpy as np
import matplotlib.pyplot as plt

with open("output.txt") as f:
    data = f.read()
    data = data.split('\n')
    
    print(data)
    try:
        x = [float(row.split(' ')[0]) for row in data]
        y1 = [float(row.split(' ')[1]) for row in data]
        y2 = [float(row.split(' ')[2]) for row in data]
    except ValueError as e:
        print("error: {0}".format(e))
    fig = plt.figure()

    ax1 = fig.add_subplot(111)

    ax1.set_title("Graph showing speed comparison between algorithms")    
    ax1.set_xlabel('Number being calculated')
    ax1.set_ylabel('Seconds required to calculate number')
    ax1.set_xscale('log')
    ax1.set_yscale('log')
    ax1.plot(x,y1, label='HS Data')
    ax1.plot(x,y2, label='Poly Data')

    leg = ax1.legend()

    plt.show()