SUMMARY = "dEQP graphics driver testing framework"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = " \
	git://github.com/KhronosGroup/VK-GL-CTS.git;protocol=https;branch=opengl-es-cts-3.2.6 \
	file://0001-Drop-useless-dependencies.patch \
	file://0002-glslang.patch \
	"
UPSTREAM_CHECK_COMMITS = "1"

SRCREV = "7d1658b55793bf2206e2f68171444c82a186c536"
PV = "3.2.6.0+git${SRCPV}"

S = "${WORKDIR}/git"

DEPENDS = "libpng zlib virtual/egl spirv-headers spirv-tools glslang"
# "amber" for vulkan

inherit cmake pkgconfig python3native ${@'features_check' if (d.getVar('LAYERSERIES_CORENAMES') not in ["zeus"]) else 'distro_features_check'}

# depends on virtual/libgl
REQUIRED_DISTRO_FEATURES += "opengl"

OECMAKE_GENERATOR = "Ninja"
EXTRA_OECMAKE = " -DDEQP_TARGET=surfaceless -DCMAKE_BUILD_TYPE=Release "

do_configure:prepend() {
    # surfaceless links against libkms and such despite not using it.
    # FIXME: make this into a patch
    sed -i '/gbm/d' ${S}/targets/surfaceless/surfaceless.cmake
    sed -i '/libkms/d' ${S}/targets/surfaceless/surfaceless.cmake
    sed -i '/libgbm/d' ${S}/targets/surfaceless/surfaceless.cmake
}

do_install() {
	install -d ${D}${datadir}/deqp

	install -d ${D}${datadir}/deqp/egl
	install -m 0755 ${B}/modules/egl/deqp-egl ${D}${datadir}/deqp/egl/deqp-egl

	install -d ${D}${datadir}/deqp/gles2
	cp -r ${B}/modules/gles2/gles2 ${D}${datadir}/deqp/gles2/
	install -m 0755 ${B}/modules/gles2/deqp-gles2 ${D}${datadir}/deqp/gles2/deqp-gles2

	install -d ${D}${datadir}/deqp/gles3
	cp -r ${B}/modules/gles3/gles3 ${D}${datadir}/deqp/gles3/
	install -m 0755 ${B}/modules/gles3/deqp-gles3 ${D}${datadir}/deqp/gles3/deqp-gles3

	install -d ${D}${datadir}/deqp/gles31
	cp -r ${B}/modules/gles31/gles31 ${D}${datadir}/deqp/gles31/
	install -m 0755 ${B}/modules/gles31/deqp-gles31 ${D}${datadir}/deqp/gles31/deqp-gles31

	install -d ${D}${datadir}/deqp/tools
	install -m 0755 ${B}/executor/executor ${D}${datadir}/deqp/tools/executor
	install -m 0755 ${B}/executor/extract-sample-lists ${D}${datadir}/deqp/tools/extract-sample-lists
	install -m 0755 ${B}/executor/extract-shader-programs ${D}${datadir}/deqp/tools/extract-shader-programs
	install -m 0755 ${B}/executor/extract-values ${D}${datadir}/deqp/tools/extract-values
	install -m 0755 ${B}/executor/merge-testlogs ${D}${datadir}/deqp/tools/merge-testlogs
	install -m 0755 ${B}/executor/testlog-to-csv ${D}${datadir}/deqp/tools/testlog-to-csv
	install -m 0755 ${B}/executor/testlog-to-junit ${D}${datadir}/deqp/tools/testlog-to-junit
	install -m 0755 ${B}/executor/testlog-to-xml ${D}${datadir}/deqp/tools/testlog-to-xml
	install -m 0644 ${S}/doc/testlog-stylesheet/testlog.css ${D}${datadir}/deqp/tools/testlog.css
	install -m 0644 ${S}/doc/testlog-stylesheet/testlog.xsl ${D}${datadir}/deqp/tools/testlog.xsl
}

FILES:${PN} = " ${datadir}/deqp "
