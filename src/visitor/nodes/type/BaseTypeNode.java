package visitor.nodes.type;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.TerminalNode;
import symobjects.SymbolTable;
import symobjects.identifierobj.TypeObj;
import symobjects.identifierobj.typeobj.scalarobj.BoolObj;
import symobjects.identifierobj.typeobj.scalarobj.CharObj;
import symobjects.identifierobj.typeobj.scalarobj.IntObj;
import symobjects.identifierobj.typeobj.scalarobj.StringObj;
import visitor.Node;

public class BaseTypeNode extends Node {
    private TypeObj type;

    public BaseTypeNode(SymbolTable currentST, ParserRuleContext ctx) {
        super(currentST, ctx);
        switch (ctx.getText()) {
            case "int": type = (IntObj) currentST.lookupAll("int");break;
            case "bool": type = (BoolObj) currentST.lookupAll("bool");break;
            case "char": type = (CharObj) currentST.lookupAll("char");break;
            case "string": type = (StringObj) currentST.lookupAll("string");break;
            default:assert(false);
        }
    }

    public TypeObj getType() {
        return type;
    }
}
