class GameException(Exception):
    def __init__(self,msg):
        self.__message=msg
    @property
    def message(self):
        return self.__message
def checkMoveInList(move,list):
    '''checks if the col of the given mov is == with one col of the given list
    the row gets updated
    it returns true if it found such a col
    false otherwise '''
    for i in list:
        if move.col==i.col:
            move.row=i.row
            return True
    return False
def isInt(x):
    ''' returns true if input x is integer,false otherwsie'''
    try:
        int(x)
        return True
    except:
        return False
def circle(canvas,x,y, r):
   ''' creates an oval and returns it. input- the canvas in which u draw,x,y-middleof the circle,r-radious '''
   id = canvas.create_oval(x-r,y-r,x+r,y+r,activefill="#FF0000")
   return id
def getBestMove(board,player,ite=0):
    ''' minimax- input state of the board and whose turn it is. returns a [move,score]. in the end u rlly
    only care bout the score. ite is just an iterable for testing purpouse '''
    legend={'_humanPlayer':'X','_aiPlayer':'0'}
    #print(ite)
    r=board.winWHO()
    if r==0:
        return [0,0]
    elif r==1:#'_humanPlayer'
        return [1,-10]
    elif r==16:
        return [1,10]
    options=board.playableCircles()
    moves=[]
    for i in options:

        move=[i,0]

        board.move(move[0],legend[player])
        if player == '_aiPlayer':
            move[1]=getBestMove(board,'_humanPlayer',ite+1)[1]
        else:
            move[1]=getBestMove(board,'_aiPlayer',ite+1)[1]
        moves.append(move)

        board.move(move[0],' ')
    bestMove=0
    if player=='_aiPlayer':
        bestScore=-10000
        for i in range(0,len(moves)):
            if moves[i][1]>bestScore:
                bestMove=i
                bestScore=moves[i][1]
    else:
        bestScore=10000
        for i in range(0,len(moves)):
            if moves[i][1]<bestScore:
                bestMove=i
                bestScore=moves[i][1]
    #print (moves[bestMove][0].row,moves[bestMove][0].col,'score',moves[bestMove][1])
    return moves[bestMove]
def get2movesAhead(board,player,ite=0):
    ''' minimax,just as above,this one being stopped after 4 moves ahead(way faster) '''
    legend={'_humanPlayer':'X','_aiPlayer':'0'}
    print(ite)
    r=board.winWHO()
    if r==0 :
        return [0,0]
    elif r==1:#'_humanPlayer'
        return [1,-10]
    elif r==16:
        return [1,10]
    if ite==4:
        return [0,0]
    options=board.playableCircles()
    moves=[]
    #print(len(options))
    for i in options:
        print(i.row,i.col)
        move=[i,0]

        board.move(move[0],legend[player])
        if player == '_aiPlayer':
            move[1]=get2movesAhead(board,'_humanPlayer',ite+1)[1]
        else:
            move[1]=get2movesAhead(board,'_aiPlayer',ite+1)[1]
        moves.append(move)

        board.move(move[0],' ')
    bestMove=0
    if player=='_aiPlayer':
        bestScore=-10000
        for i in range(0,len(moves)):
            if moves[i][1]>bestScore:
                bestMove=i
                bestScore=moves[i][1]
    else:
        bestScore=10000
        for i in range(0,len(moves)):
            if moves[i][1]<bestScore:
                bestMove=i
                bestScore=moves[i][1]
    print (moves[bestMove][0].row,moves[bestMove][0].col,'score',moves[bestMove][1])
    return moves[bestMove]
