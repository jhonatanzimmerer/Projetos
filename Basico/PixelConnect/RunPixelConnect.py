import cv2
from matplotlib import pyplot
import numpy

from cls import *
from cls.ViewImg import ViewImg

"""obj_viewimg = ViewImg("Syngenta1.bmp")
obj_viewimg.start()"""


img = "img/Sygenta.bmp"

obj_img = cv2.imread(img)
"""obj_img = cv2.cvtColor(obj_img, cv2.COLOR_BGR2RGB)"""

pyplot.imshow(obj_img)
pyplot.show()

