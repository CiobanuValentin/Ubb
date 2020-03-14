package Model.Value;
import Model.Type.Type;
import Model.Type.IntType;

public class IntValue implements Value {
    int val;
    public IntValue(int v){val=v;}

    public int getVal() {return val;}
    public String toString() { return ""+val;}
    public Type getType() { return new IntType();}
    public boolean equals(Object another){
        if(another instanceof IntValue){
            return (((IntValue) another).getVal()==(this.getVal()));
        }
        return false;
    }
}
