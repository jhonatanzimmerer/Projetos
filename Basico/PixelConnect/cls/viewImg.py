import cv2
import img
from matplotlib import pyplot


class ViewImg:

    def __init__(self, imagem):
        self.obj_img = cv2.imread(imagem)
        self.obj_img = cv2.cvtColor(self.obj_img, cv2.COLOR_BGR2RGB)

    def start(self):
        pyplot.imshow(self.obj_img)
        pyplot.show()

    def definircor(self, y, x, tom, canal):
        self.obj_img.itemset((y, x, canal), tom)

    def limparcor(self, velhacor, novacor):
        altura, largura, canais = self.obj_img.shape

        for y in range(0, altura):
            for x in range(0, largura):
                posicao = self.obj_img[y][x]
                cor = []

                for posicaoTemp in posicao:
                    cor.append(posicaoTemp)

                if cor[0] == velhacor[0] and cor[1] == velhacor[1] and cor[2] == velhacor[2]:
                    self.definircor(y, x, novacor[0], 0)
                    self.definircor(y, x, novacor[1], 1)
                    self.definircor(y, x, novacor[2], 2)
