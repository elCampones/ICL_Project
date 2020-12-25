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

public class ASTIf implements ASTNode{

    private final ASTNode ifNode, thenNode, elseNode;

    public ASTIf(ASTNode ifNode, ASTNode thenNode, ASTNode elseNode) {
        this.ifNode   = ifNode;
        this.thenNode = thenNode;
        this.elseNode = elseNode;
    }

    @Override
    public IValue eval(Environment<IValue> e) throws IDDeclaredTwiceException, UndeclaredIdentifierException, TypeErrorException {
        IValue ifVal = ifNode.eval(e);
        if (ifVal instanceof VBool) {
            if(((VBool) ifVal).isTrue())
                thenNode.eval(e);
            else elseNode.eval(e);
            return new VVoid();
        }
        throw new TypeErrorException("The condition did not evaluate to a boolean");
    }

    @Override
    public void compile(CodeBlock codeBlock, Environment<Coordinates> env) throws IDDeclaredTwiceException, UndeclaredIdentifierException {
    	
    	Label thenLabel = new Label();
    	Label exit = new Label();
    	ifNode.compile(codeBlock, env);
    	codeBlock.addOperation(new PushValueOp("1"));
    	codeBlock.addOperation(new SubOp());
    	codeBlock.addOperation(new EqualOp(thenLabel));
    	elseNode.compile(codeBlock, env);
    	codeBlock.addOperation(new GoToOp(exit));
    	codeBlock.addOperation(new LabelOp(thenLabel));
    	thenNode.compile(codeBlock, env);
    	codeBlock.addOperation(new LabelOp(exit)); 
    	
    }

    @Override
    public IType typeCheck(Environment<IType> e)
            throws TypeErrorException, IDDeclaredTwiceException,
            UndeclaredIdentifierException {
        if (ifNode.typeCheck(e) instanceof TBool) {
            thenNode.typeCheck(e);
            elseNode.typeCheck(e);
            return new TVoid();
        }
        throw new TypeErrorException("If condition must be type Bool.");
    }
}
