class Circle():
    """ 1 cerc din cele 6x7 """
    def __init__(self,row,col):
        self.__row=row
        self.__col=col
    @property
    def row(self):
        return self.__row

    @property
    def col(self):
        return self.__col
    @row.setter
    def row(self, value):
        self.__row = value
