package Model.Value;

import Model.Type.StringType;
import Model.Type.Type;

public class StringValue implements Value{
    private String string;

    public StringValue(String str){
        string=str;
    }

    public String getVal(){
        return string;
    }

    @Override
    public String toString(){
        return string;
    }

    @Override
    public boolean equals(Object other){
        if(other instanceof StringValue){
            return (((StringValue) other).getVal().equals(this.getVal()));
        }
        return false;
    }

    @Override
    public Type getType() {
        return new StringType();
    }
}
