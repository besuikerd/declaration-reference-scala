package org.metaborg.pixiedust.lang.ast

object MPixieDust {
  // Generic imports
  import org.metaborg.scalaterms
  import org.metaborg.scalaterms.{ sdf, STerm }
  // Generated imports
  import org.metaborg.pixiedust.lang.ast.MCommon._
  // Lexical definitions
  // Define implicit conversions (e.g. in the package object) to another representation you prefer
  case class SKeyword(string: java.lang.String, origin: scalaterms.Origin) extends sdf.Lexical {
    override def toSTerm: STerm.String = STerm.String(string, Some(origin))
  }
  // Lexical extractors
  object SKeyword extends scalaterms.TermLikeCompanion[SKeyword] {
    override val fromSTerm: scalaterms.FromSTerm[SKeyword] = new scalaterms.FromSTerm[SKeyword] {
      override def unapply(term: STerm): Option[SKeyword] = term match {
        case STerm.String(string, origin) => origin.map(origin => SKeyword(string, origin))
        case _ => scala.None
      }
    }
  }
  // Sort definitions
  sealed trait SStart extends sdf.Constructor
  sealed trait SIDs extends sdf.Constructor
  // Constructor definitions
  object SStart extends scalaterms.TermLikeCompanion[SStart] {
    override val fromSTerm: scalaterms.FromSTerm[SStart] = new scalaterms.FromSTerm[SStart] {
      override def unapply(term: STerm): Option[SStart] = term match {
        case IDs1.fromSTerm(start1) => scala.Some(start1)
        case _ => scala.None
      }
    }

    case class IDs1(ids1: STerm.List[SIDs], origin: scalaterms.Origin) extends SStart {
      override def toSTerm = STerm.Cons("IDs", scala.List(ids1.toSTerm), Some(origin))
    }
    object IDs1 extends scalaterms.TermLikeCompanion[IDs1] {
      override val fromSTerm: scalaterms.FromSTerm[IDs1] = new scalaterms.FromSTerm[IDs1] {
        override def unapply(term: STerm): Option[IDs1] = term match {
          case STerm.Cons("IDs", scala.List(SIDs.fromSTerm.list(ids1)), o) => o.map(o => IDs1(ids1, o))
          case _ => None
        }
      }
    }
  }
  object SIDs extends scalaterms.TermLikeCompanion[SIDs] {
    override val fromSTerm: scalaterms.FromSTerm[SIDs] = new scalaterms.FromSTerm[SIDs] {
      override def unapply(term: STerm): Option[SIDs] = term match {
        case Def1.fromSTerm(ids1) => scala.Some(ids1)
        case Ref1.fromSTerm(ids1) => scala.Some(ids1)
        case _ => scala.None
      }
    }

    case class Def1(id1: SID, origin: scalaterms.Origin) extends SIDs {
      override def toSTerm = STerm.Cons("Def", scala.List(id1.toSTerm), Some(origin))
    }
    object Def1 extends scalaterms.TermLikeCompanion[Def1] {
      override val fromSTerm: scalaterms.FromSTerm[Def1] = new scalaterms.FromSTerm[Def1] {
        override def unapply(term: STerm): Option[Def1] = term match {
          case STerm.Cons("Def", scala.List(SID.fromSTerm(id1)), o) => o.map(o => Def1(id1, o))
          case _ => None
        }
      }
    }
    case class Ref1(id1: SID, origin: scalaterms.Origin) extends SIDs {
      override def toSTerm = STerm.Cons("Ref", scala.List(id1.toSTerm), Some(origin))
    }
    object Ref1 extends scalaterms.TermLikeCompanion[Ref1] {
      override val fromSTerm: scalaterms.FromSTerm[Ref1] = new scalaterms.FromSTerm[Ref1] {
        override def unapply(term: STerm): Option[Ref1] = term match {
          case STerm.Cons("Ref", scala.List(SID.fromSTerm(id1)), o) => o.map(o => Ref1(id1, o))
          case _ => None
        }
      }
    }
  }
}