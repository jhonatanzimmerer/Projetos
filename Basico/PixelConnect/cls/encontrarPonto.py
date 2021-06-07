
from cls.viewImg import ViewImg


class EncontrarPonto:

    def __init__(self, imagem, cor):
        self.obj_viewimg = ViewImg(imagem)
        self.corponto = cor

    '''Validar se acor é igual o que procuro'''
    def validarcor(self, y, x):
        if self.obj_viewimg.obj_img.item(y, x, 0) == self.corponto[0]:
            if self.obj_viewimg.obj_img.item(y, x, 1) == self.corponto[1]:
                if self.obj_viewimg.obj_img.item(y, x, 2) == self.corponto[2]:
                    return True
        return False
    '''Encontrar o primeiro pixel que contem a cor procurada'''
    def pontoinicio(self, altura, largura):
        for y in range(0, altura):
            for x in range(0, largura):
                validar = self.validarcor(y, x)
                if validar:
                    return y, x
    '''Identificar o ponto mais proximo'''
    '''Calcular o ponto mais proximo'''
    def pontoproximo(self, pontos, referencia):
        distancia = (((pontos[0][1] - referencia[1]) ** 2) + ((pontos[0][0] - referencia[0]) ** 2)) ** (1/2)
        resultado = {"y": pontos[0][0], "x": pontos[0][1], "distancia": distancia}

        for ponto in range(1,len(pontos)):
            distancia = (((ponto[1] - referencia[1]) ** 2) + ((ponto[0] - referencia[0]) ** 2)) ** (1 / 2)
            if distancia < resultado.get("distancia"):
                resultado.__setitem__("y", ponto[0])
                resultado.__setitem__("x", ponto[1])
                resultado.__setitem__("distancia", distancia)

        return resultado.get("y"), resultado.get("x")

    def procurarponto(self):
        tentativa = 0
        resultado = []
        ponto = []
        altura, largura, canais = self.obj_viewimg.obj_img.shape
        posicao = self.pontoinicio(altura, largura)
        if posicao[0] > 0:
            resultado.append(self.superior(posicao[0], posicao[1], tentativa, largura))
        if posicao[1] < largura:
            resultado.append(self.direita(posicao[0], posicao[1]))
        resultado.append(self.inferior(posicao[0], posicao[1]))
        resultado.append(self.esquerda(posicao[0], posicao[1]))

        for linha in range(0, int(len(resultado))):
            if not resultado[linha] == (0, 0):
                ponto.append(resultado[linha])

        if len(ponto)<=0:
            tentativa += 1
        else:
            pass
            """Encontrei o ponto"""
    '''Fazer a varredura nos pixels a cima do ponto indicado'''
    def superior(self, y, x, tentativa, largura):
        ponto = [y, x]
        resultado = []
        y -= 1
        inicio = 0
        fim = 0
        if x - (1 + tentativa) <= 0:
            inicio = 0
        else:
            inicio = x - (1 + tentativa)

        if x + (1 + tentativa) >= largura:
            fim = largura
        else:
            fim = x + (1 + tentativa)

        del x

        for eixox in range(inicio, fim):
            validar = self.validarcor(y, eixox)
            if validar:
                resultado.append([y, eixox])

        return self.pontoproximo(resultado, ponto)
    '''Fazer a varredura nos pixels a direita do ponto indicado'''
    def direita(self, y, x, tentativa, largura):
        ponto = [y,x]
        x = x + (1 + tentativa)

        if 

        for repetir in range(0,1):
            validar = self.validarcor(y, x)
            if validar:
                return y, x
            x += 1
        return 0, 0
    '''Fazer a varredura nos pixels a baixo do ponto indicado'''
    def inferior(self, y, x):
        y += 1
        x -= 1
        for repetir in range(0, 3):
            validar = self.validarcor(y, x)
            if validar:
                return y, x
            x += 1
        return 0, 0
    '''Fazer a varredura nos pixels a esquerda do ponto indicado'''
    def esquerda(self, y, x):
        x -= 1
        for repetir in range(0, 1):
            validar = self.validarcor(y, x)
            if validar:
                return y, x
            x += 1
        return 0, 0

    ''' 
    def pontoproximo(self, resultado,ponto):
        for linha in range(0, int(len(resultado))):
            ponto[0]-resultado[linha]
            '''
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

