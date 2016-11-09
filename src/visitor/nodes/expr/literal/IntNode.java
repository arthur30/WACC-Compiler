package visitor.nodes.expr.literal;

import main.CompileTimeError;
import org.antlr.v4.runtime.ParserRuleContext;
import symobjects.SymbolTable;
import symobjects.identifierobj.typeobj.scalarobj.IntObj;
import visitor.nodes.expr.LiteralNode;

public class IntNode extends LiteralNode {
    private int value;

    public IntNode(SymbolTable currentST, ParserRuleContext ctx) {
        super(currentST, ctx);
        this.type = new IntObj(currentST);
        check(ctx.getText());
    }

    public IntNode(SymbolTable currentST, String value) {
        super(currentST, null);
        this.type = new IntObj(currentST);
        check(value);
    }

    private void check(String textValue) {
        try {
            this.value = Integer.parseInt(textValue);
        } catch (NumberFormatException e) {
            addError(CompileTimeError.INTEGER_OVERFLOW);
            printSyntacticErrors();
            System.exit(CompileTimeError.EXIT_SYNTAX_ERROR);
        }
    }

    public int getValue() {
        return value;
    }
}