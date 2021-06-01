
from cls.viewImg import ViewImg


class EncontrarPonto:

    def __init__(self, imagem, cor):
        self.obj_viewimg = ViewImg(imagem)
        self.corponto = cor

    def validarcor(self, y, x):
        if self.obj_viewimg.obj_img.item(y, x, 0) == self.corponto[0]:
            if self.obj_viewimg.obj_img.item(y, x, 1) == self.corponto[1]:
                if self.obj_viewimg.obj_img.item(y, x, 2) == self.corponto[2]:
                    return True
        return False

    def pontoinicio(self, altura, largura):
        for y in range(0, altura):
            for x in range(0, largura):
                validar = self.validarcor(y, x)
                if validar:
                    return y, x

    def procurarponto(self):
        altura, largura, canais = self.obj_viewimg.obj_img.shape
        posicao = self.pontoinicio(altura, largura)
        """print(posicao[0], posicao[1])"""

    def superior(self, y, x):
        y = y-1
        
        validar = self.validarcor(y, x)


    def direita(self):

    def inferior(self):

    def esquerda(self):


"""    def pontoproximo(self):

    def direita(self, altura, largura):
        for y in range(0,altura):
            for x in range(0, largura):
                self.obj_viewimg.obj_img.item(y, y, 0)
        return
        pass

    def diagonal(self, altura, largura):
        pass

    def baixo(self, altura, largura):
        pass"""

