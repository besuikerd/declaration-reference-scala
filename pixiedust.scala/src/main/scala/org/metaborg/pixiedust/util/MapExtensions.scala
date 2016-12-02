package org.metaborg.pixiedust.util

object MapExtensions {
  implicit class MultiMapExtensions[K, V](val map: Map[K, Seq[V]]) extends AnyVal{
    def addBinding(k: K, v: V): Map[K, Seq[V]] = {
      map.get(k) match {
        case Some(seq) => map + (k -> (v +: seq))
        case None => map + (k -> Seq(v))
      }
    }

    def addBinding(kv: (K, V)): Map[K, Seq[V]] = addBinding(kv._1, kv._2)
  }
}
