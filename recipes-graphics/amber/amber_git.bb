SUMMARY = "Amber"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = " \
	git://github.com/google/amber.git;protocol=https \
	"
UPSTREAM_CHECK_COMMITS = "1"

SRCREV = "d26ee22dd7faab1845a531d410f7ec1db407402a"
PV = "0.1+git${SRCPV}"

S = "${WORKDIR}/git"

inherit cmake pkgconfig python3native distro_features_check

# depends on virtual/libgl
REQUIRED_DISTRO_FEATURES += "opengl"

OECMAKE_GENERATOR = "Ninja"
EXTRA_OECMAKE = " -DCMAKE_BUILD_TYPE=Release \
	-DAMBER_SKIP_TESTS=TRUE -DAMBER_SKIP_SPIRV_TOOLS=TRUE \
	-DAMBER_SKIP_SHADERC=TRUE -DAMBER_SKIP_SAMPLES=TRUE \
	"
