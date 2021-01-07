package parser;
/* Generated By:JavaCC: Do not edit this line. Parser.java */
/** ID lister. */
import tree.*;
import environment.*;
import tree.binaryArithmetic.*;
import tree.boolArithmetic.*;
import tree.comparisons.*;
import java.util.Collection;
import java.util.LinkedList;
import dataTypes.IValue;

public class Parser implements ParserConstants {
  public static final GlobalEnvironment globalEnv = new GlobalEnvironment();

  /** Main entry point. */
  public static void main(String [] args)
  {
    Parser parser = new Parser(System.in);
    ASTNode exp;
    while (true)
    {
      try
      {
        exp = parser.Start();
        if (exp != null)
        {
          IValue val = exp.eval(new Environment < dataTypes.IValue > ());
          if (val != null)
          System.out.println(val);
        }
      }
      catch (Exception e)
      {
        System.out.println("Syntax Error!");
        parser.ReInit(System.in);
      }
    }
  }

  static final public ASTNode Start() throws ParseException {
  ASTNode t = null;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case PRINT:
    case DEF:
    case NEW:
    case BOOL:
    case IF:
    case WHILE:
    case ID:
    case NUM:
    case MINUS:
    case LPAR:
    case NOT:
    case DEREF:
    case SEMI:
      t = ExpSeq();
      break;
    case 0:
      jj_consume_token(0);
      break;
    default:
      jj_la1[0] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    {if (true) return t;}
    throw new Error("Missing return statement in function");
  }

  static final public ASTNode ExpSeq() throws ParseException {
  ASTNode t;
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case SEMI:
        ;
        break;
      default:
        jj_la1[1] = jj_gen;
        break label_1;
      }
      jj_consume_token(SEMI);
    }
    t = Exp();
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case SEMI:
        ;
        break;
      default:
        jj_la1[2] = jj_gen;
        break label_2;
      }
      jj_consume_token(SEMI);
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case EL:
      jj_consume_token(EL);
      break;
    case PRINT:
    case DEF:
    case NEW:
    case BOOL:
    case IF:
    case WHILE:
    case ID:
    case NUM:
    case MINUS:
    case LPAR:
    case NOT:
    case DEREF:
    case SEMI:
      t = ExpSeq();
      break;
    default:
      jj_la1[3] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    {if (true) return t;}
    throw new Error("Missing return statement in function");
  }

/*
*   Sequence of expressions, the value returned is that of the last expression
*/
  static final public ASTNode ExpSeq2() throws ParseException {
  ASTNode lastExp;
    lastExp = Exp();
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case SEMI:
        ;
        break;
      default:
        jj_la1[4] = jj_gen;
        break label_3;
      }
      label_4:
      while (true) {
        jj_consume_token(SEMI);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case SEMI:
          ;
          break;
        default:
          jj_la1[5] = jj_gen;
          break label_4;
        }
      }
      lastExp = new ASTSemi(lastExp, Exp());
    }
    {if (true) return lastExp;}
    throw new Error("Missing return statement in function");
  }

  static final public ASTNode Exp() throws ParseException {
  ASTNode expRes;
    expRes = FunctionalExp();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case EQUALS:
    case GREATER:
    case GREATER_EQ:
    case SMALLER:
    case SMALLER_EQ:
    case AND:
    case OR:
    case ATTR_VAL:
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case ATTR_VAL:
        jj_consume_token(ATTR_VAL);
      expRes = new ASTAttr(expRes, Exp());
        break;
      case EQUALS:
        jj_consume_token(EQUALS);
      expRes = new ASTEquals(expRes, FunctionalExp());
        break;
      case GREATER:
        jj_consume_token(GREATER);
      expRes = new ASTGreater(expRes, FunctionalExp());
        break;
      case GREATER_EQ:
        jj_consume_token(GREATER_EQ);
      expRes = new ASTGreaterEq(expRes, FunctionalExp());
        break;
      case SMALLER:
        jj_consume_token(SMALLER);
      expRes = new ASTSmaller(expRes, FunctionalExp());
        break;
      case SMALLER_EQ:
        jj_consume_token(SMALLER_EQ);
      expRes = new ASTSmallerEq(expRes, FunctionalExp());
        break;
      case AND:
        jj_consume_token(AND);
      expRes = new ASTAnd(expRes, FunctionalExp());
        break;
      case OR:
        jj_consume_token(OR);
      expRes = new ASTOr(expRes, FunctionalExp());
        break;
      default:
        jj_la1[6] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
    default:
      jj_la1[7] = jj_gen;
      ;
    }
    {if (true) return expRes;}
    throw new Error("Missing return statement in function");
  }

/*
* Function that will return a value at the end
 */
  static final public ASTNode FunctionalExp() throws ParseException {
  ASTNode termRes;
    termRes = Term();
    label_5:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case PLUS:
      case MINUS:
        ;
        break;
      default:
        jj_la1[8] = jj_gen;
        break label_5;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case PLUS:
        jj_consume_token(PLUS);
      termRes = new ASTPlus(termRes, Term());
        break;
      case MINUS:
        jj_consume_token(MINUS);
      termRes = new ASTSub(termRes, Term());
        break;
      default:
        jj_la1[9] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    {if (true) return termRes;}
    throw new Error("Missing return statement in function");
  }

  static final public ASTNode Term() throws ParseException {
  ASTNode factRes;
    factRes = Fact();
    label_6:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case TIMES:
      case DIV:
      case REMAINDER:
        ;
        break;
      default:
        jj_la1[10] = jj_gen;
        break label_6;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case TIMES:
        jj_consume_token(TIMES);
      factRes = new ASTMult(factRes, Fact());
        break;
      case DIV:
        jj_consume_token(DIV);
      factRes = new ASTDiv(factRes, Fact());
        break;
      case REMAINDER:
        jj_consume_token(REMAINDER);
      factRes = new ASTRem(factRes, Fact());
        break;
      default:
        jj_la1[11] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    {if (true) return factRes;}
    throw new Error("Missing return statement in function");
  }

  static final public ASTNode Fact() throws ParseException {
  Token n;
  ASTNode t;;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case PRINT:
      jj_consume_token(PRINT);
      t = new ASTPrintln(Exp());
      break;
    case NUM:
      n = jj_consume_token(NUM);
      t = new ASTNum(Integer.parseInt(n.image));
      break;
    case BOOL:
      n = jj_consume_token(BOOL);
      t = new ASTBool(n.image);
      break;
    case ID:
      n = jj_consume_token(ID);
                t = isFunc(n);
      break;
    case MINUS:
      jj_consume_token(MINUS);
      t = new ASTNeg(Fact());
      break;
    case DEREF:
      jj_consume_token(DEREF);
      t = new ASTDeref(Fact());
      break;
    case NEW:
      jj_consume_token(NEW);
      t = new ASTNew(FunctionalExp());
      break;
    case WHILE:
      jj_consume_token(WHILE);
      t = Exp();
      jj_consume_token(DO);
      t = new ASTWhile(t, ExpSeq2());
      jj_consume_token(END);
      break;
    case DEF:
      jj_consume_token(DEF);
      t = DefType();
      break;
    case LPAR:
      jj_consume_token(LPAR);
      t = ExpSeq2();
      jj_consume_token(RPAR);
      break;
    case IF:
      jj_consume_token(IF);
      t = If();
      break;
    case NOT:
      jj_consume_token(NOT);
      t = new ASTNot(Fact());
      break;
    default:
      jj_la1[12] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    {if (true) return t;}
    throw new Error("Missing return statement in function");
  }

  static final public ASTNode isFunc(Token n) throws ParseException {
  ASTNode t;
    t = new ASTVariable(n.image);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case LPAR:
      jj_consume_token(LPAR);
              t = FuncSolver(n);
      break;
    default:
      jj_la1[13] = jj_gen;
      ;
    }
    {if (true) return t;}
    throw new Error("Missing return statement in function");
  }

  static final public ASTNode FuncSolver(Token id) throws ParseException {
  Token n;
  ASTNode t;
  Collection < ASTNode > args = new LinkedList < ASTNode > ();
    label_7:
    while (true) {
      n = jj_consume_token(NUM);
      t = new ASTNum(Integer.parseInt(n.image));
      args.add(t);
      label_8:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case COMMA:
          ;
          break;
        default:
          jj_la1[14] = jj_gen;
          break label_8;
        }
        jj_consume_token(COMMA);
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case NUM:
        ;
        break;
      default:
        jj_la1[15] = jj_gen;
        break label_7;
      }
    }
    jj_consume_token(RPAR);
    {if (true) return new ASTFuncSolver(id.toString(), args);}
    throw new Error("Missing return statement in function");
  }

  static final public ASTNode DefType() throws ParseException {
  ASTNode t;
  Token id, aux;
  String type;
    type = null;
    id = jj_consume_token(ID);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case DECLARE_TYPE:
      jj_consume_token(DECLARE_TYPE);
      type = "";
      label_9:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case INTEGER_REF:
          aux = jj_consume_token(INTEGER_REF);
          break;
        case BOOL_REF:
          aux = jj_consume_token(BOOL_REF);
          break;
        case GENERAL_REF:
          aux = jj_consume_token(GENERAL_REF);
          break;
        default:
          jj_la1[16] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
        type = String.format("%s %s", type, aux.image).trim();
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case INTEGER_REF:
        case BOOL_REF:
        case GENERAL_REF:
          ;
          break;
        default:
          jj_la1[17] = jj_gen;
          break label_9;
        }
      }
      break;
    default:
      jj_la1[18] = jj_gen;
      ;
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ATTR_ID:
      jj_consume_token(ATTR_ID);
      t = Def(id, type);
      break;
    case LPAR:
      jj_consume_token(LPAR);
      t = Func(id);
      break;
    default:
      jj_la1[19] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    {if (true) return t;}
    throw new Error("Missing return statement in function");
  }

  static final public ASTNode Def(Token id, String type) throws ParseException {
  ASTNode body, t;
  Token id2, aux2;
  String type2;
  Collection < Variable > variables = new LinkedList < Variable > ();
    variables.add(new Variable(id.toString(), type, Exp()));
    label_10:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case ID:
        ;
        break;
      default:
        jj_la1[20] = jj_gen;
        break label_10;
      }
      type2 = null;
      id2 = jj_consume_token(ID);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case DECLARE_TYPE:
        jj_consume_token(DECLARE_TYPE);
        type2 = "";
        label_11:
        while (true) {
          switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
          case INTEGER_REF:
            aux2 = jj_consume_token(INTEGER_REF);
            break;
          case BOOL_REF:
            aux2 = jj_consume_token(BOOL_REF);
            break;
          case GENERAL_REF:
            aux2 = jj_consume_token(GENERAL_REF);
            break;
          default:
            jj_la1[21] = jj_gen;
            jj_consume_token(-1);
            throw new ParseException();
          }
          type2 = String.format("%s %s", type2, aux2.image).trim();
          switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
          case INTEGER_REF:
          case BOOL_REF:
          case GENERAL_REF:
            ;
            break;
          default:
            jj_la1[22] = jj_gen;
            break label_11;
          }
        }
        break;
      default:
        jj_la1[23] = jj_gen;
        ;
      }
      jj_consume_token(ATTR_ID);
      variables.add(new Variable(id2.toString(), type2, Exp()));
    }
    t = new ASTGlobalDef(variables);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case IN:
      jj_consume_token(IN);
      body = ExpSeq2();
      jj_consume_token(END);
      t = new ASTDef(variables, body);
      break;
    default:
      jj_la1[24] = jj_gen;
      ;
    }
    {if (true) return t;}
    throw new Error("Missing return statement in function");
  }

  static final public ASTNode Func(Token name) throws ParseException {
  Token id, aux;
  String type, functype;
  Collection < Variable > args = new LinkedList < Variable > ();
  ASTNode body;
    label_12:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case ID:
        ;
        break;
      default:
        jj_la1[25] = jj_gen;
        break label_12;
      }
      type = null;
      id = jj_consume_token(ID);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case DECLARE_TYPE:
        jj_consume_token(DECLARE_TYPE);
        type = "";
        label_13:
        while (true) {
          switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
          case INTEGER_REF:
            aux = jj_consume_token(INTEGER_REF);
            break;
          case BOOL_REF:
            aux = jj_consume_token(BOOL_REF);
            break;
          case GENERAL_REF:
            aux = jj_consume_token(GENERAL_REF);
            break;
          default:
            jj_la1[26] = jj_gen;
            jj_consume_token(-1);
            throw new ParseException();
          }
          type = String.format("%s %s", type, aux.image).trim();
          switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
          case INTEGER_REF:
          case BOOL_REF:
          case GENERAL_REF:
            ;
            break;
          default:
            jj_la1[27] = jj_gen;
            break label_13;
          }
        }
        break;
      default:
        jj_la1[28] = jj_gen;
        ;
      }
      args.add(new Variable(id.toString(), type, null));
      label_14:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case COMMA:
          ;
          break;
        default:
          jj_la1[29] = jj_gen;
          break label_14;
        }
        jj_consume_token(COMMA);
      }
    }
    jj_consume_token(RPAR);
    functype = null;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case DECLARE_TYPE:
      jj_consume_token(DECLARE_TYPE);
      functype = "";
      label_15:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case INTEGER_REF:
          aux = jj_consume_token(INTEGER_REF);
          break;
        case BOOL_REF:
          aux = jj_consume_token(BOOL_REF);
          break;
        case GENERAL_REF:
          aux = jj_consume_token(GENERAL_REF);
          break;
        default:
          jj_la1[30] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
        functype = String.format("%s %s", functype, aux.image).trim();
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case INTEGER_REF:
        case BOOL_REF:
        case GENERAL_REF:
          ;
          break;
        default:
          jj_la1[31] = jj_gen;
          break label_15;
        }
      }
      break;
    default:
      jj_la1[32] = jj_gen;
      ;
    }
    jj_consume_token(ATTR_ID);
    body = ExpSeq2();
    jj_consume_token(END);
    {if (true) return new ASTFunc(name.toString(), functype, args, body);}
    throw new Error("Missing return statement in function");
  }

  static final public ASTNode If() throws ParseException {
  ASTNode ifRes, thenRes, elseRes;
    elseRes = new ASTVoid();
    ifRes = Exp();
    jj_consume_token(THEN);
    thenRes = ExpSeq2();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ELSE:
      jj_consume_token(ELSE);
      elseRes = ExpSeq2();
      break;
    default:
      jj_la1[33] = jj_gen;
      ;
    }
    jj_consume_token(END);
    {if (true) return new ASTIf(ifRes, thenRes, elseRes);}
    throw new Error("Missing return statement in function");
  }

  static private boolean jj_initialized_once = false;
  /** Generated Token Manager. */
  static public ParserTokenManager token_source;
  static SimpleCharStream jj_input_stream;
  /** Current token. */
  static public Token token;
  /** Next token. */
  static public Token jj_nt;
  static private int jj_ntk;
  static private int jj_gen;
  static final private int[] jj_la1 = new int[34];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x22c2731,0x0,0x0,0xa2c2730,0x0,0x0,0xf0000000,0xf0000000,0x300000,0x300000,0x1c00000,0x1c00000,0x22c2730,0x2000000,0x0,0x80000,0x38000,0x38000,0x0,0x2000000,0x40000,0x38000,0x38000,0x0,0x40,0x40000,0x38000,0x38000,0x0,0x0,0x38000,0x38000,0x0,0x1000,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x128,0x100,0x100,0x128,0x100,0x100,0x47,0x47,0x0,0x0,0x0,0x0,0x28,0x0,0x200,0x0,0x0,0x0,0x80,0x10,0x0,0x0,0x0,0x80,0x0,0x0,0x0,0x0,0x80,0x200,0x0,0x0,0x80,0x0,};
   }

  /** Constructor with InputStream. */
  public Parser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public Parser(java.io.InputStream stream, String encoding) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser.  ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new ParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 34; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 34; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public Parser(java.io.Reader stream) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new ParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 34; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 34; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public Parser(ParserTokenManager tm) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 34; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(ParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 34; i++) jj_la1[i] = -1;
  }

  static private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  static final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  static final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  static private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  static private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  static private int[] jj_expentry;
  static private int jj_kind = -1;

  /** Generate ParseException. */
  static public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[42];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 34; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 42; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  static final public void enable_tracing() {
  }

  /** Disable tracing. */
  static final public void disable_tracing() {
  }

}
