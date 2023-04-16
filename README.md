# multiplatform-theme

⚠️ [Ongoing project]

A Multiplatform Theme, developed to mimic the components native look on different platforms

Based on the initial Tweet of @joreilly:
- https://twitter.com/joreilly/status/1646819478183858177

And @JimSproch:
- https://twitter.com/JimSproch/status/1647016821365825536
- 

**Project Structure**
- androidApp
- iosApp
- shared-theme
The native app theme. It extends MaterialTheme and (in the future) it will seamlessly blend with the platform aesthetics
- shared-ui
The UI is shared across Android and iOS.

**Current limitations**
- Unable to load a specific `painterResource` from **shared-theme**.
My best guess is that it's being bundle in the final .ipa.