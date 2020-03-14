from circle import Circle
from tools import GameException,isInt
from game import Game
class UI:
    def __init__(self):
        self.__game=Game()
    def _read_Move(self):
        y=input('Gimmie col(1-7):').strip()
        if not isInt(y):
            raise GameException('Give a valid number,not a char...')
        y=int(y)
        y-=1
        return y
    def start(self):
        ok=True
        while ok:
            print('0-Quit')
            print('1-Player vs Player')
            print('2-Player vs AI(it takes a lot for each move)')
            print('3-Player vs decent AI')
            n=input().strip()
            if n=='0':
                print('Bye')
                ok=False
            elif n=='1':
                self.startPVP()
                self.__game=Game()
                #ok=False
            elif n=='2':
                self.startPVAI()
                self.__game=Game()
            elif n=='3':
                self.startPVAI2()
                self.__game=Game()
            else:
                print('Wrong input,try again')
    def startPVAI(self):
        b=self.__game.board
        pMove=True
        while not b.win() and not b.tie():
            if pMove:
                self.printMatrix(b)
                gMove=True
                while gMove:
                    try:
                        y=self._read_Move()
                        self.__game.moveHuman(Circle(-1,y),'X')
                        gMove=False
                    except GameException as e:
                        print(e)
                        gMove=True
            else:
                self.__game.moveComputer()
            pMove=not pMove
        print('Game over!')
        self.printMatrix(b)
        if b.win():
            if pMove:
                print('Computer wins')
            else:
                print('Player wins')
        else:
            print("It's a tie")
    def startPVAI2(self):
        b=self.__game.board
        pMove=True
        while not b.win() and not b.tie():
            if pMove:
                self.printMatrix(b)
                gMove=True
                while gMove:
                    try:
                        y=self._read_Move()
                        self.__game.moveHuman(Circle(-1,y),'X')
                        gMove=False
                    except GameException as e:
                        print(e)
                        gMove=True
            else:
                self.__game.moveComputer2()
            pMove=not pMove
        print('Game over!')
        self.printMatrix(b)
        if b.win():
            if pMove:
                print('Computer wins')
            else:
                print('Player wins')
        else:
            print("It's a tie")
    def startPVP(self):
        b=self.__game.board
        p1Move=True
        while not b.win() and not b.tie():
            self.printMatrix(b)
            gMove=True
            while gMove:
                try:
                    y=self._read_Move()
                    if p1Move:
                        self.__game.moveHuman(Circle(-1,y),'X')
                    else:
                        self.__game.moveHuman(Circle(-1,y),'0')
                    #p1Move=not p1Move
                    gMove=False
                except GameException as e:
                    print(e)
                    gMove=True
            p1Move=not p1Move
        print('Game over!')
        self.printMatrix(b)
        if b.win():
            if p1Move==False:
                print('Player 1 wins')
            else:
                print('Player 2 wins')
        else:
            print("It's a tie")
    def printMatrix(self,b):
        print("  1   2   3   4   5   6   7")
        print(b)
