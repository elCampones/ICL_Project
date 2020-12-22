package tree;

import compiler.CodeBlock;
import compiler.Coordinates;
import compiler.Label;
import compiler.operations.EqualOp;
import compiler.operations.GoToOp;
import compiler.operations.LabelOp;
import compiler.operations.PushValueOp;
import compiler.operations.SubOp;
import dataTypes.IValue;
import dataTypes.TypeErrorException;
import dataTypes.VBool;
import environment.Environment;
import environment.exceptions.IDDeclaredTwiceException;
import environment.exceptions.UndeclaredIdentifierException;

public class ASTEquals implements ASTNode {

    private final ASTNode l, r;

    public ASTEquals(ASTNode l, ASTNode r) {
        this.l = l;
        this.r = r;
    }

    @Override
    public IValue eval(Environment<IValue> e)
            throws IDDeclaredTwiceException, UndeclaredIdentifierException,
            TypeErrorException {
        return new VBool(l.eval(e).equals(r.eval(e)));
    }

    @Override
    public void compile(CodeBlock codeBlock, Environment<Coordinates> env) throws IDDeclaredTwiceException, UndeclaredIdentifierException {
    	
    	Label thenLabel = new Label();
    	Label exit = new Label();
    	
    	l.compile(codeBlock, env);
    	r.compile(codeBlock, env);
    	codeBlock.addOperation(new SubOp());
    	codeBlock.addOperation(new EqualOp(thenLabel));
    	codeBlock.addOperation(new PushValueOp("0"));
    	codeBlock.addOperation(new GoToOp(exit));
    	codeBlock.addOperation(new LabelOp(thenLabel));
    	codeBlock.addOperation(new PushValueOp("1"));
    	codeBlock.addOperation(new LabelOp(exit));
    }
}
