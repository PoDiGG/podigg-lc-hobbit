# PoDiGG Linked Connections for HOBBIT
_POpulation DIstribution-based Gtfs Generator_

[![Docker Automated Build](https://img.shields.io/docker/automated/podigg/podigg-lc-hobbit.svg)](https://hub.docker.com/r/podigg/podigg-lc-hobbit/)

A HOBBIT dataset generator wrapper for PoDiGG.
More information on the usage of this mimicking algorithm is available at the [HOBBIT wiki](https://github.com/hobbit-project/platform/wiki/Transferring-mimicked-data).

## Docker

Downloading the image from the Docker hub:
```bash
docker pull podigg/podigg-lc-hobbit
```

Building the image from this repo:
```bash
git clone --recursive git@github.com:PoDiGG/podigg-lc-hobbit.git
cd podigg-lc-hobbit
docker build -t podigg-lc-hobbit .
```

Parameters must be passed using environment variables.

## Parameters

All generator parameters can be configured using environment variables.
More information about this can be found at [PoDiGG](https://github.com/PoDiGG/podigg).

For big data generation, the `NODE_MEM` environment variable can be overridden to increase the maximum memory usage.
This can be done by adding the `-e NODE_MEM=<memory in MB>` option when running the Docker container.

# License
The PoDiGG generator is written by [Ruben Taelman](http://rubensworks.net/).

This code is copyrighted by [Ghent University – imec](http://idlab.ugent.be/)
and released under the [MIT license](http://opensource.org/licenses/MIT).