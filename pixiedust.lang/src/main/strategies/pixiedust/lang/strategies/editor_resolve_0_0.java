package pixiedust.lang.strategies;

import org.spoofax.interpreter.terms.IStrategoTerm;
import org.strategoxt.lang.Context;
import org.strategoxt.lang.Strategy;
import org.metaborg.pixiedust.PixieDustEditorServices;

public class editor_resolve_0_0 extends Strategy {

	public static editor_resolve_0_0 instance = new editor_resolve_0_0();

	@Override
	public IStrategoTerm invoke(Context context, IStrategoTerm current) {
		return PixieDustEditorServices.editorResolve(context, current);
	}

}
