/**
 * 
 */
package com.stroe.admin.directive;

import java.io.Writer;

import com.jfinal.template.Env;
import com.jfinal.template.expr.ast.Expr;
import com.jfinal.template.expr.ast.ExprList;
import com.jfinal.template.expr.ast.Logic;
import com.jfinal.template.stat.Scope;
import com.stroe.admin.directive.annotation.Directive;

/**
 * @author taoge
 * @version 1.0
 * @create_at 2017年9月9日上午8:58:13
 */
@Directive(name="if")
public class IF extends BaseDirective{

    private Expr valueExpr;
	
	@Override
	public void setExprList(ExprList exprList) {
 		valueExpr = exprList.getExprArray()[0];
	}
	
	@Override
	public void exec(Env env, Scope scope, Writer writer) {
		Object object = valueExpr.eval(scope);
		if(Logic.isTrue(object))
		   stat.exec(env, scope, writer);
	}
	
	@Override
	public boolean hasEnd() {
		return true;
	}

}
