package org.metaborg.pixiedust

import org.metaborg.pixiedust.lang.ast.MCommon.SID
import org.metaborg.pixiedust.lang.ast.MPixieDust.SIDs
import org.metaborg.pixiedust.lang.ast.MPixieDust.SIDs.{Def1, Ref1}
import org.metaborg.pixiedust.lang.ast.MPixieDust.SStart.IDs1
import org.metaborg.scalaterms.{Origin, STerm, TermLike}
import org.metaborg.scalaterms.spoofax.EditorServices
import org.metaborg.scalaterms.implicits._
import org.metaborg.pixiedust.util.MapExtensions.MultiMapExtensions

trait ModelService {

  type DeclarationMapping = Map[String, Seq[Origin]]
  type ReferenceMapping = Map[String, Seq[Origin]]

  case class AnalysisModel(
    declarations: DeclarationMapping,
    references: ReferenceMapping
  )

  def buildModel: TermLike => AnalysisModel = {
    case IDs1.fromSTerm(IDs1(ids, _)) => {
        val (declarationMapping, referenceMapping) = ids.foldLeft[(DeclarationMapping, ReferenceMapping)]((Map.empty, Map.empty)){
          case ((declarations, references), id) => id match{
            case Def1(SID(name, origin), _) => (declarations.addBinding(name, origin), references)
            case Ref1(SID(name, origin), _) => (declarations, references.addBinding(name, origin))
          }
        }
        AnalysisModel(declarationMapping, referenceMapping)
      }
    case term => throw new Error(s"Error building model, mismatched ast: ${term}")
  }


  def getDeclaration(model: AnalysisModel, name: String): Option[Origin] =
    model.declarations.get(name).flatMap{
      case d +: _ => Some(d)
      case _ => None
    }
}
