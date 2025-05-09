/***********************************************************************
 * Copyright (c) 2013-2025 General Atomics Integrated Intelligence, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, Version 2.0
 * which accompanies this distribution and is available at
 * http://www.opensource.org/licenses/apache2.0.php.
 ***********************************************************************/

package org.locationtech.geomesa.filter

import org.geotools.api.filter.{And, Filter}
import org.geotools.filter.visitor.DefaultFilterVisitor

import scala.collection.JavaConverters._

// This class helps us split a Filter into pieces if there are ANDs at the top.
class AndSplittingFilter extends DefaultFilterVisitor {

  // This function really returns a Seq[Filter].
  override def visit(filter: And, data: scala.Any): AnyRef = {
    filter.getChildren.asScala.flatMap { subfilter =>
      this.visit(subfilter, data)
    }
  }

  def visit(filter: Filter, data: scala.Any): Seq[Filter] = {
    filter match {
      case a: And => visit(a, data).asInstanceOf[Seq[Filter]]
      case _     => Seq(filter)
    }
  }
}
