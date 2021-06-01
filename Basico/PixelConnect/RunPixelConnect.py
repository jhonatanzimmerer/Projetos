import cv2
from matplotlib import pyplot
import numpy

from cls.viewImg import *


obj_viewimg = ViewImg("img/Syngenta.bmp")

obj_viewimg.limparcor((0, 0, 0), (255, 255, 255))
obj_viewimg.start()

