import numpy as np
import cv2
from matplotlib import pyplot as plt

data = cv2.imread("img/Syngenta.bmp")

"""data = cv2.cvtColor(data, cv2.COLOR_BGR2RGB)"""
altura, largura, canais = data.shape
valores = []

for y in range(altura-1, altura):
    for x in range(largura-1, largura):
        comp = data[y][x]
        for comp1 in comp:
            print(comp1)
        valores.append(data[y][x])

print(valores)
"""plt.imshow(data)
plt.show()"""