package org.metaborg.pixiedust

import org.metaborg.pixiedust.lang.ast.MCommon.SID
import org.metaborg.pixiedust.lang.ast.MPixieDust.SIDs.Ref1
import org.metaborg.scalaterms.spoofax.{EditorResolve, FocusedStrategyInput, ResolutionResult}
import org.strategoxt.lang.Context

trait ResolveService { this: EditorResolve
  with ModelService =>
  override def editorResolve(focusedStrategyInput: FocusedStrategyInput)(implicit context: Context): Option[ResolutionResult] = {
    val model = buildModel(focusedStrategyInput.ast)
    focusedStrategyInput.node match {
      case Ref1.fromSTerm(Ref1(SID(name, _), _)) => getDeclaration(model, name).map(ResolutionResult.apply)
      case _ => None
    }
  }
}
