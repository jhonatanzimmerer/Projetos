
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
    def pontoinicio(self, altura, largura, eixoy, eixox):
        for y in range(eixoy + 1, altura):
            for x in range(eixox + 1, largura):
                validar = self.validarcor(y, x)
                if validar:
                    return y, x
    '''Identificar o ponto mais proximo'''
    '''Calcular o ponto mais proximo'''
    def pontoproximo(self, pontos, referencia):
        if not len(pontos) == 0:
            distancia = (((pontos[0][1] - referencia[1]) ** 2) + ((pontos[0][0] - referencia[0]) ** 2)) ** (1/2)
            resultado = {"y": pontos[0][0], "x": pontos[0][1], "distancia": distancia}

            for ponto in range(1,len(pontos)):
                distancia = (((ponto[1] - referencia[1]) ** 2) + ((ponto[0] - referencia[0]) ** 2)) ** (1 / 2)
                if distancia < resultado.get("distancia"):
                    resultado.__setitem__("y", ponto[0])
                    resultado.__setitem__("x", ponto[1])
                    resultado.__setitem__("distancia", distancia)

            return resultado.get("y"), resultado.get("x")
        return [-1, -1]

    def procurarponto(self):
        tentativa = 1
        resultado = []
        ponto = []
        altura, largura, canais = self.obj_viewimg.obj_img.shape
        eixoy = -1
        eixox = -1
        for repetir in range(0, 298):
            posicao = self.pontoinicio(altura, largura, eixoy, eixox)
            for y in range(posicao[0], altura):
                if not tentativa == -1:
                    for x in range(posicao[1], largura):
                        resultado.append(self.superior(y, x, tentativa, largura))
                        resultado.append(self.direita(y, x, tentativa, largura, altura))
                        resultado.append(self.inferior(y, x, tentativa, largura, altura))
                        resultado.append(self.esquerda(y, x, tentativa, altura))
                        tentativa += 1
                        for repetir in range(0, resultado.count([-1,-1])):
                            resultado.pop(resultado.index([-1, -1]))
                        if len(resultado) > 0:
                            ponto.append(self.pontoproximo(resultado, [y, x]).copy())
                            resultado.clear()
                            tentativa = -1
                            break
                else:
                    break
        print(ponto)

    '''Fazer a varredura nos pixels a cima do ponto indicado'''
    def superior(self, y, x, tentativa, largura):
        ponto = [y,x]
        resultado = []
        inicio = 0
        fim = 0

        if y - tentativa <= 0:
            y = 0
        else:
            y = y - tentativa

        if x - tentativa <= 0:
            inicio = 0
        else:
            inicio = x - tentativa
        if x + tentativa >= largura:
            fim = largura
        else:
            fim = x + tentativa
        del x

        for eixox in range(inicio, fim):
            validar = self.validarcor(y, eixox)
            if validar:
                resultado.append([y, eixox])

        return self.pontoproximo(resultado, ponto)
    '''Fazer a varredura nos pixels a direita do ponto indicado'''
    def direita(self, y, x, tentativa, largura, altura):
        ponto = [y,x]
        resultado = []
        inicio = 0
        fim = 0
        if x + tentativa >= largura:
            x = largura
        else:
            x = x + tentativa

        if y - (tentativa-1) <= 1:
            inicio = 1
        else:
            inicio = y - (tentativa-1)
        if y + (tentativa-1) >= altura - 1:
            fim = altura -1
        else:
            fim =  y + (tentativa-1)
        del y

        for eixoy in range(inicio, fim):
            validar = self.validarcor(eixoy, x)
            if validar:
                resultado.append([eixoy, x])

        return self.pontoproximo(resultado, ponto)
    '''Fazer a varredura nos pixels a baixo do ponto indicado'''
    def inferior(self, y, x, tentativa, largura, altura):
        ponto = [y, x]
        resultado = []
        inicio = 0
        fim = 0

        if y + tentativa >= altura:
            y = altura
        else:
            y = y + tentativa

        if x - tentativa <= 0:
            inicio = 0
        else:
            inicio = x - tentativa
        if x + tentativa >= largura:
            fim = largura
        else:
            fim = x + tentativa
        del x

        for eixox in range(inicio, fim):
            validar = self.validarcor(y, eixox)
            if validar:
                resultado.append([y, eixox])

        return self.pontoproximo(resultado, ponto)
    '''Fazer a varredura nos pixels a esquerda do ponto indicado'''
    def esquerda(self, y, x, tentativa, altura):
        ponto = [y, x]
        resultado = []
        inicio = 0
        fim = 0
        if x - tentativa <= 0:
            x = 0
        else:
            x = x - tentativa

        if y - (tentativa - 1) <= 1:
            inicio = 1
        else:
            inicio = y - (tentativa - 1)
        if y + (tentativa - 1) >= altura - 1:
            fim = altura - 1
        else:
            fim = y + (tentativa - 1)
        del y

        for eixoy in range(inicio, fim):
            validar = self.validarcor(eixoy, x)
            if validar:
                resultado.append([eixoy, x])

        return self.pontoproximo(resultado, ponto)

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

