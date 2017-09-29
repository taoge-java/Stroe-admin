/**
 * 
 */
package com.stroe.admin.directive;

import java.io.Writer;

import com.jfinal.template.Env;
import com.jfinal.template.expr.ast.Expr;
import com.jfinal.template.expr.ast.ExprList;
import com.jfinal.template.stat.ParseException;
import com.jfinal.template.stat.Scope;
import com.stroe.admin.directive.annoation.Directive;
import com.stroe.admin.util.NumberTools;

/**
 * @author taoge
 * @version 1.0
 * @create_at 2017年9月9日上午8:58:39
 */
@Directive(name="number")
public class Number extends BaseDirective{
	
    private Expr valueExpr;
	
    private static final NumberTools number = NumberTools.geNumberTools();
	
	@Override
	public void setExprList(ExprList exprList) {
 		if(exprList.length() >1){
 			throw new ParseException("Wrong number parameter of #number directive, one parameters allowed at most", location);
		}
 		valueExpr = exprList.getExprArray()[0];
	}
	
	@Override
	public void exec(Env env, Scope scope, Writer writer) {
		Object object = valueExpr.eval(scope);
		write(writer,number.toNumber(object));
	}
}
