import cv2
from matplotlib import pyplot
import numpy

from cls.encontrarPonto import EncontrarPonto
from cls.viewImg import ViewImg


"""obj_viewimg = ViewImg("img/Syngenta.bmp")
obj_viewimg.limparcor((0, 0, 0), (255, 255, 255))"""


obj_encontrarponto = EncontrarPonto("img/Syngenta.bmp", (96, 192, 0))
obj_encontrarponto.obj_viewimg.limparcor((0, 0, 0), (255, 255, 255))

obj_encontrarponto.procurarponto()
"""obj_encontrarponto.obj_img.start()"""

