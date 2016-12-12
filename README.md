# PoDiGG Linked Connections for HOBBIT
_POpulation DIstribution-based Gtfs Generator_

A HOBBIT dataset generator wrapper for PoDiGG.

Make sure to clone this repo recursively to ensure that the submodule is downloaded:
```bash
git clone --recursive ifigit@git.informatik.uni-leipzig.de:taelman/podigg-lc-hobbit.git
```

Building the container:
```bash
mvn package -Dmaven.test.skip=true
docker build -t podigg-lc-hobbit .
```

All generator parameters can be configured using environment variables.
More information about this can be found at https://git.datasciencelab.ugent.be/rtaelman/podigg or https://git.informatik.uni-leipzig.de/taelman/podigg

For big data generation, the `NODE_MEM` environment variable can be overridden to increase the maximum memory usage.
This can be done by adding the `-e NODE_MEM=<memory in MB>` option when running the Docker container.
