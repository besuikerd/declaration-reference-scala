package org.metaborg.pixiedust

import org.metaborg.scalaterms.spoofax.{EditorServices, GeneralStrategyInput}

object PixieDustEditorServices extends EditorServices
  with ModelService
  with AnalysisService
  with HoverService
  with ResolveService