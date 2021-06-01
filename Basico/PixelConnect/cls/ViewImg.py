import cv2
import img
from matplotlib import pyplot


class ViewImg():

    def __init__(self, img):
        print("PAssei aqui")
        self.obj_img = cv2.imread(img)
        self.obj_img = cv2.cvtColor(self.obj_img, cv2.COLOR_BGR2RGB)

    def start(self):
        pyplot.imshow(self.obj_img)
        pyplot.show()
