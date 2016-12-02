package org.metaborg.pixiedust

import org.metaborg.scalaterms.spoofax.{EditorHover, EditorServices, FocusedStrategyInput, HoverResult}
import org.strategoxt.lang.Context

trait HoverService { this: EditorHover =>
  override def editorHover(focusedStrategyInput: FocusedStrategyInput)(implicit context: Context): Option[HoverResult] =
    Some(HoverResult(s"Hovering <b>${focusedStrategyInput.node}</b>"))
}