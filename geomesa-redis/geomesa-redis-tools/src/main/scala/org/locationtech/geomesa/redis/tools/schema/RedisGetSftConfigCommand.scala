/***********************************************************************
 * Copyright (c) 2013-2025 General Atomics Integrated Intelligence, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, Version 2.0
 * which accompanies this distribution and is available at
 * http://www.opensource.org/licenses/apache2.0.php.
 ***********************************************************************/

package org.locationtech.geomesa.redis.tools.schema

import com.beust.jcommander.Parameters
import org.locationtech.geomesa.redis.data.RedisDataStore
import org.locationtech.geomesa.redis.tools.RedisDataStoreCommand
import org.locationtech.geomesa.redis.tools.RedisDataStoreCommand.RedisDataStoreParams
import org.locationtech.geomesa.redis.tools.schema.RedisGetSftConfigCommand.RedisGetSftConfigParameters
import org.locationtech.geomesa.tools.RequiredTypeNameParam
import org.locationtech.geomesa.tools.status.{GetSftConfigCommand, GetSftConfigParams}

class RedisGetSftConfigCommand extends GetSftConfigCommand[RedisDataStore] with RedisDataStoreCommand {
  override val params = new RedisGetSftConfigParameters
}

object RedisGetSftConfigCommand {
  @Parameters(commandDescription = "Get the SimpleFeatureType definition of a schema")
  class RedisGetSftConfigParameters extends RedisDataStoreParams with GetSftConfigParams with RequiredTypeNameParam
}
