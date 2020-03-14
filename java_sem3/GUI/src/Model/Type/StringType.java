package Model.Type;

import Model.Value.StringValue;
import Model.Value.Value;

public class StringType implements Type {
    public boolean equals(Object other){
        return other instanceof StringType;
    }

    @Override
    public Value defaultValue() {
        return new StringValue("");
    }

    public String toString(){return "string";}
}
