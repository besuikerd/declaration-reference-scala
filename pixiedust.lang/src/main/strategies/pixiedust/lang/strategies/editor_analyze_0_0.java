package pixiedust.lang.strategies;

import org.spoofax.interpreter.terms.IStrategoTerm;
import org.strategoxt.lang.Context;
import org.strategoxt.lang.Strategy;

import org.metaborg.pixiedust.PixieDustEditorServices;

public class editor_analyze_0_0 extends Strategy {

	public static editor_analyze_0_0 instance = new editor_analyze_0_0();

	@Override
	public IStrategoTerm invoke(Context context, IStrategoTerm current) {
		IStrategoTerm analysis = PixieDustEditorServices.editorAnalyze(context, current);
		if(analysis == null){
			context.getIOAgent().printError("Null analysis");
		}
		return analysis;
	}

}
