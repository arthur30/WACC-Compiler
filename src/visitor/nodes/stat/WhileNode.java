package visitor.nodes.stat;

import antlr.WACCParser;
import codegen.Instruction;
import codegen.operands.Register;
import main.CompileTimeError;
import symobjects.SymbolTable;
import symobjects.identifierobj.typeobj.scalarobj.BoolObj;
import visitor.nodes.ExprNode;
import visitor.nodes.StatNode;

import java.util.LinkedHashSet;
import java.util.LinkedList;

public class WhileNode extends StatNode<WACCParser.WhileStatContext> {

    private StatNode statNode;

    public WhileNode(SymbolTable currentST, WACCParser.WhileStatContext ctx,
                     ExprNode exprNode, StatNode statNode) {
        super(currentST, ctx);

        if (exprNode.hasErrors() || statNode.hasErrors()) {
            setError();
            return;
        }

        this.statNode = statNode;

        checkWhileCondition(exprNode);
    }

    private void checkWhileCondition(ExprNode exprNode) {
        if (!(exprNode.getType() instanceof BoolObj)) {
            addSemanticError(CompileTimeError.INCOMPATIBLE_TYPE,
                    "while condition", "BOOL", exprNode.getCtx().getText(),
                    exprNode.getType().toString());
        }
    }

    public StatNode getStatNode() {
        return statNode;
    }

    @Override
    public LinkedList<Instruction> generateInstructions(LinkedHashSet<Register> availableRegisters) {
        return null;
    }
}
