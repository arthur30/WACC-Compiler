package codegen;

import codegen.operands.Register;
import visitor.nodes.ProgramNode;

import java.util.ArrayList;
import java.util.List;

public class CodeGenerator {
    public static final String INSTRUCTION_NAME_FORMAT = "%-5s";
    private DataDir dataDir;
    private List<Instruction> mainDir;
    private List<LibFunc> libDir;

    public CodeGenerator(ProgramNode start) {
        this.dataDir = new DataDir();
        this.mainDir = start.generateInstructions(this, Register.allRegisters());
        this.libDir = new ArrayList<>();
    }

    public void useLibFunc(Class<? extends LibFunc> funcClass) {
        for (LibFunc func : libDir) {
            if (funcClass.isInstance(func)) {
                return;
            }
        }
        LibFunc func = null;
        try {
            func = funcClass.getConstructor(DataDir.class).newInstance(dataDir);
        } catch(Exception e) { assert(false): "CodeGenerator: Should not get here"; }

        libDir.add(func);

        for (Class<? extends LibFunc> dep : func.getDependencies()) {
            useLibFunc(dep);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(dataDir.toString());

        sb.append(".text\n\n.global main\n");

        for (Instruction ins : mainDir) {
            sb.append(ins.toString());
        }

        for (LibFunc func : libDir) {
            for (Instruction ins : func.getInstructions()) {
                sb.append(ins.toString());
            }
        }

        return sb.toString();
    }
}