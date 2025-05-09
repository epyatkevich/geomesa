.. _fsds_parameters:

FileSystem Data Store Parameters
================================

Use the following parameters for a FileSystem data store (required parameters are marked with ``*``):

=============================== ====== ===================================================================================
Parameter                       Type   Description
=============================== ====== ===================================================================================
``fs.path *``                   String The root path to write and read data from (e.g. s3a://mybucket/datastores/testds)
``fs.encoding``                 String The file encoding used when creating a new schema. If not specified here, it must
                                       be configured with ``geomesa.fs.encoding`` in the SimpleFeatureType user data.
                                       Provided implementations are ``parquet`` and ``orc``.
``fs.read-threads``             Int    The number of threads used for queries
``fs.writer.partition.timeout`` String Timeout for closing a partition file after write, e.g. '60 seconds'. This is to
                                       prevent too many open files during large write operations.
``fs.config.paths``             String Additional Hadoop configuration resource files (comma-delimited)
``fs.config.xml``               String Additional Hadoop configuration properties, as a standard XML ``<configuration>``
                                       element
``geomesa.query.timeout``       String The max time a query will be allowed to run before being killed. The
                                       timeout is specified as a duration, e.g. ``1 minute`` or ``60 seconds``
``geomesa.security.auths``      String  Comma-delimited superset of authorizations that will be used for queries
=============================== ====== ===================================================================================

Programmatic Access
-------------------

An instance of a FileSystem data store can be obtained through the normal GeoTools discovery methods, assuming that
the GeoMesa code is on the classpath:

.. code-block:: java

    Map<String, String> parameters = new HashMap<>;
    parameters.put("fs.path", "hdfs://localhost:9000/fs-root/");
    org.geotools.api.data.DataStore dataStore =
        org.geotools.api.data.DataStoreFinder.getDataStore(parameters);

More information on using GeoTools can be found in the `GeoTools user guide <https://docs.geotools.org/stable/userguide/>`_.
