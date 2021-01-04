package tree.binaryArithmetic;

import java.io.IOException;

import compiler.CodeBlock;
import compiler.Coordinates;
import compiler.operations.DivOp;
import dataTypes.*;
import environment.Environment;
import environment.exceptions.IDDeclaredTwiceException;
import environment.exceptions.NotEnoughArgumentsException;
import environment.exceptions.UndeclaredIdentifierException;
import tree.ASTNode;

/**
* MIEI
* @author Ana Josefa Matos - 49938
* @author Pedro Campon�s - 50051
**/

public class ASTDiv extends ASTIntArithmetic {

    public ASTDiv(ASTNode l, ASTNode r) {
        super(l, r);
    }

    @Override
    public IValue eval(Environment<IValue> e)
            throws IDDeclaredTwiceException, UndeclaredIdentifierException, 
            TypeErrorException, NotEnoughArgumentsException {
    	IValue lRes, rRes;
    	if ((lRes = l.eval(e)) instanceof VInt && (rRes = r.eval(e)) instanceof VInt)
    		return new VInt(((VInt)lRes).getVal() / ((VInt)rRes).getVal());
   		throw new TypeErrorException("Expressions are not integers");
    }

    @Override
    public void compile(CodeBlock cb, Environment<Coordinates> envCoord, Environment<IType> envTypes)
    		throws IDDeclaredTwiceException, UndeclaredIdentifierException, TypeErrorException, IOException {
    	l.compile(cb, envCoord, envTypes);
    	r.compile(cb, envCoord, envTypes);
    	cb.addOperation(new DivOp());
    }
}
