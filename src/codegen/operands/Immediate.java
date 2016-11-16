package codegen.operands;

import codegen.Operand;

public class Immediate implements Operand {

    private String value;

    public Immediate(int value) {
        this.value = "=" + value;
    }

    @Override
    public String toString() {
        return value;
    }

}
