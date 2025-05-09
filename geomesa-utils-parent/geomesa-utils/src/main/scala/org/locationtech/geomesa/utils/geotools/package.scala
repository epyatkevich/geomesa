/***********************************************************************
 * Copyright (c) 2013-2025 General Atomics Integrated Intelligence, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, Version 2.0
 * which accompanies this distribution and is available at
 * http://www.opensource.org/licenses/apache2.0.php.
 ***********************************************************************/

package org.locationtech.geomesa.utils

import org.geotools.api.referencing.crs.CoordinateReferenceSystem
import org.geotools.geometry.jts.ReferencedEnvelope
import org.geotools.referencing.CRS
import org.geotools.referencing.crs.DefaultGeographicCRS
import org.locationtech.geomesa.utils.text.WKTUtils
import org.locationtech.jts.geom.{Geometry, Polygon}

import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

package object geotools {

  // use the epsg jar if it's available (e.g. in geoserver), otherwise use the less-rich constant
  lazy val CRS_EPSG_4326: CoordinateReferenceSystem =
    try { CRS.decode("EPSG:4326", true) } catch { case _: Throwable => DefaultGeographicCRS.WGS84 }

  lazy val CrsEpsg4326: CoordinateReferenceSystem = CRS_EPSG_4326

  // we make this a function, as envelopes are mutable
  def wholeWorldEnvelope = new ReferencedEnvelope(-180, 180, -90, 90, CRS_EPSG_4326)
  val WholeWorldPolygon: Polygon =
    WKTUtils.read("POLYGON((-180 -90, 180 -90, 180 90, -180 90, -180 -90))").asInstanceOf[Polygon]
  val EmptyGeometry: Geometry = WKTUtils.read("POLYGON EMPTY")

  // date format with geotools pattern
  val GeoToolsDateFormat: DateTimeFormatter =
    DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").withZone(ZoneOffset.UTC)
}
