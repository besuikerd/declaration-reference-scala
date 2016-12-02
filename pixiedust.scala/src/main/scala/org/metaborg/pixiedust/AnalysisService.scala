package org.metaborg.pixiedust

import org.metaborg.scalaterms.Origin
import org.metaborg.scalaterms.spoofax._
import org.strategoxt.lang.Context

trait AnalysisService { this: EditorAnalyze with ModelService =>

  override def editorAnalyze(generalStrategyInput: GeneralStrategyInput)(implicit context: Context): AnalysisResult = {
    val model = buildModel(generalStrategyInput.ast)
    AnalysisResult(
      ast = generalStrategyInput.ast,
      errors = findErrors(model),
      warnings = List.empty,
      notes = List.empty
    )
  }


  def findErrors(model: AnalysisModel): List[EditorMessage] = {
    val duplicateDeclarations = findDuplicateDeclarations(model).map{
      case (name, o) => EditorMessage(s"duplicate declaration: ${name}", o)
    }.toList

    val undeclaredReferences = findUndeclaredReferences(model).map{
      case (name, o) => EditorMessage(s"undeclared reference: ${name}", o)
    }.toList

    duplicateDeclarations ++ undeclaredReferences
  }

  def findDuplicateDeclarations(model: AnalysisModel): Seq[(String, Origin)] =
    for {
      (name, origins) <- model.declarations.filter(_._2.length > 1).toSeq
      origin <- origins
    } yield (name, origin)

  def findUndeclaredReferences(model: AnalysisModel): Seq[(String, Origin)] =
    for {
      (name, origins) <- model.references.toSeq if getDeclaration(model, name).isEmpty
      origin <- origins
    } yield (name, origin)
}
