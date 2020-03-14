import copy
from circle import Circle
from texttable import Texttable
class Board():
    def __init__(self):#reprezentare tabla joc
        self._data=[[0]*7,[0]*7,[0]*7,[0]*7,[0]*7,[0]*7]
        #self._data2=[[0]*7,[0]*7,[0]*7,[0]*7,[0]*7,[0]*7]
        self.initi()
    def initi(self):
        ''' initialize the board with zero'''
        for i in range(6):
            for j in range(7):
                self._data[i][j]=0
    def copy(self):#copy tabla joc
        x=Board()
        x._data=copy.deepcopy(self._data)
        return x
    def playableCircles(self):
        ''' returns a list of circles ,the playable ones'''
        circles=[]
        for j in range(7):
            for i in range(5,-1,-1):
                if self._data[i][j]==0:
                    circles.append(Circle(i,j))
                    break
        #for i in circles:
        #    print(i.row,i.col)
        return circles
    def _checkLines(self):
        '''check if it is a win on each line. returns[true/false,1/16]-1 for player win,16 for ai'''
        for i in range(0,6):
            for j in range(0,4):
                s=1
                for x in range(0,4):
                     s*=self._data[i][j+x]
                if s==16 or s==1 :
                    return [True,s]
        return [False,0]
    def _checkCol(self):
        '''just as in check lines,but for col'''
        for j in range(0,7):
            for i in range(0,3):
                s=1
                for x in range(0,4):
                    s*=self._data[i+x][j]
                if s==16 or s==1:
                    return [True,s]
        return [False,0]
    def _checkDiagonals(self):#verifica din nou cond de linie pe verti
        '''just as in check lines and col,but on diagonals'''
        for i in range(0,3):
            for j in range(0,4):
                s=1
                for x in range(0,4):
                    s*=self._data[i+x][j+x]
                if s==16 or s==1:
                    return [True,s]
        for i in range(3,6):
            for j in range(0,4):
                s=1
                for x in range(0,4):
                    s*=self._data[i-x][j+x]
                if s==16 or s==1:
                    return [True,s]
        return [False,0]
    def win(self):
        '''checks if it is a win.returns true if yes,false otherwise'''
        return self._checkCol()[0] or self._checkLines()[0] or self._checkDiagonals()[0]
    def winWHO(self):
        '''returns 0 if tie,1 if player win,16 if ai win'''
        if self.tie():
            return 0
        elif self.win():
            #legend={1:'_humanPlayer',16:'_aiPlayer'}
            s=self._checkCol()
            s1=self._checkLines()
            s2=self._checkDiagonals()
            if s[0]:
                return s[1]#legend[s[1]]
            elif s1[0]:
                return s1[1]#legend[s1[1]]
            else:
                return s2[1]#legend[s2[1]]
        else:
            return -1
    def tie(self):
        '''returns true if it is a tie,false otherwise'''
        _t=self.playableCircles()
        if not self.win()and len(_t)==0:
            return True
        return False
    def move(self,circless,color):
        '''makes a move on the map. waits for a color to say so,space/x /0 and places it on the board'''
        legend={" ":0,'X':1,"0":2}
        self._data[circless.row][circless.col]=legend[color]
    def __str__(self):
        '''draws the table,returns it as a string'''
        t=Texttable()
        legend={0:" ",1:"X",2:"0"}

        for i in range(0,6):
            lista=self._data[i][:]
            for j in range(0,7):
                lista[j]=legend[lista[j]]
            t.add_row(lista)
        #t.add_row(['1','2','3','4','5','6','7'])
        return t.draw()
