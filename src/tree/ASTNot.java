package tree;

import compiler.CodeBlock;
import compiler.Coordinates;
import compiler.Label;
import compiler.operations.EqualOp;
import compiler.operations.GoToOp;
import compiler.operations.LabelOp;
import compiler.operations.PushValueOp;
import compiler.operations.SubOp;
import dataTypes.*;
import environment.Environment;
import environment.exceptions.IDDeclaredTwiceException;
import environment.exceptions.UndeclaredIdentifierException;

public class ASTNot implements ASTNode{

    private final ASTNode node;

    public ASTNot(ASTNode node) {
        this.node = node;
    }

    @Override
    public IValue eval(Environment<IValue> e) throws IDDeclaredTwiceException, UndeclaredIdentifierException, TypeErrorException {
        IValue val = node.eval(e);
        if (val instanceof VBool)
            return new VBool(!((VBool) val).isTrue());
        throw new TypeErrorException("Cannot perform 'not' operation on non boolean expressions");
    }

    @Override
    public void compile(CodeBlock codeBlock, Environment<Coordinates> env) throws IDDeclaredTwiceException, UndeclaredIdentifierException {
    	
    	codeBlock.addOperation(new PushValueOp("1"));
    	node.compile(codeBlock, env);
    	codeBlock.addOperation(new SubOp());
    	
    }

    @Override
    public IType typeCheck(Environment<IType> e)
            throws TypeErrorException, IDDeclaredTwiceException,
            UndeclaredIdentifierException {
        if (node.typeCheck(e) instanceof TBool)
            return new TBool();
        throw new TypeErrorException("Not operation requires an expression of type Bool");
    }
}
