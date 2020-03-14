package Model.Type;

import Model.Value.RefValue;
import Model.Value.Value;

public class RefType implements Type {
    private Type inner;
    public RefType(Type inner) {
        this.inner=inner;
    }
    public Type getInner() {
        return inner;
    }

    @Override
    public boolean equals(Object other){
        if (other instanceof RefType)
            return inner.equals(((RefType)other).getInner());
        else
            return false;
    }
    @Override
    public String toString() { return "Ref(" +inner.toString()+")";}
    @Override
    public Value defaultValue() {
        return new RefValue(0,inner);
    }
}
