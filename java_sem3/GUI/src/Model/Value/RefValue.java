package Model.Value;

import Model.Type.RefType;
import Model.Type.Type;

public class RefValue implements Value {
    private int address;
    private Type locationType;
    public RefValue(int address,Type locationType){
        this.address=address;
        this.locationType=locationType;
    }
    public int getAddr() {
        return address;
    }
    public Type getLocationType(){
        return locationType;
    }
    @Override
    public Type getType() {
        return new RefType(locationType);
    }
    @Override
    public String toString(){
        return "("+address+";"+locationType.toString()+")";
    }
    @Override
    public boolean equals(Object other){
        if(other instanceof RefValue){
            if(address!=((RefValue)other).getAddr())
                return false;
            return locationType.equals(((RefValue) other).getType());//think getlocationtype
        }
        return false;
    }
}
