package tree;

import compiler.CodeBlock;
import compiler.Coordinates;
import compiler.operations.PushValueOp;
import dataTypes.*;
import environment.Environment;
import environment.exceptions.IDDeclaredTwiceException;
import environment.exceptions.UndeclaredIdentifierException;

public class ASTBool implements ASTNode {

    private final boolean val;

    public ASTBool(String strVal) {
        val = Boolean.parseBoolean(strVal);
    }

    @Override
    public IValue eval(Environment<IValue> e) {
        return new VBool(val);
    }

    @Override
    public void compile(CodeBlock codeBlock, Environment<Coordinates> env) {
        if(val)
        	codeBlock.addOperation(new PushValueOp("1"));
        else
        	codeBlock.addOperation(new PushValueOp("0"));
    }

    @Override
    public IType typeCheck(Environment<IType> e) {
        return new TBool();
    }
}
