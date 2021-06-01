import cv2


'''Carregando a imagem'''
obj_imagem = cv2.imread("img/Syngenta.bmp")

'''Definindo o modelo de cor para RGB'''
obj_imagem = cv2.cvtColor(obj_imagem, cv2.COLOR_BGR2RGB)

'''Definindo variaveis'''
altura, largura, canais = obj_imagem.shape
valores = []

'''Carregando pixel'''
'''Loop para percorrer os pixels'''
for y in range(0, altura):
    for x in range(0, largura):
        comp = obj_imagem[y][x]
        cor = []

        for comp1 in comp:
            cor.append(comp1)

        '''Separação das cores'''
        if not (cor[0] == 0 and cor[1] == 0 and cor[2] == 0):
            if not (cor[0] == 255 and cor[1] == 255 and cor[2] == 255):
                if cor[0] == 96 and cor[1] == 192 and cor[2] == 0:
                    valores.append(cor.copy())

        '''Limpar a lista'''
        cor.clear()

print("Quantidade de pixels verdes: ", len(valores))
