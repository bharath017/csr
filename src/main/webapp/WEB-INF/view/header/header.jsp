<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<spring:url value="/static/" var="url"/>
<spring:url value="" var="contextPathURl"/>

<nav class="site-navbar navbar navbar-default navbar-fixed-top navbar-mega navbar-expand-md"
    role="navigation">
    <div class="navbar-header">
      <button type="button" class="navbar-toggler hamburger hamburger-close navbar-toggler-left hided"
        data-toggle="menubar">
        <span class="sr-only">Toggle navigation</span>
        <span class="hamburger-bar"></span>
      </button>
      <button type="button" class="navbar-toggler collapsed" data-target="#site-navbar-collapse"
        data-toggle="collapse">
        <i class="icon wb-more-horizontal" aria-hidden="true"></i>
      </button>
      <div class="navbar-brand navbar-brand-center site-gridmenu-toggle" data-toggle="gridmenu">
        <img class="navbar-brand-logo" src="${url}assets/images/logo.png" title="Remark">
        <span class="navbar-brand-text hidden-xs-down"> CSR School</span>
      </div>
      <button type="button" class="navbar-toggler collapsed" data-target="#site-navbar-search"
        data-toggle="collapse">
        <span class="sr-only">Toggle Search</span>
        <i class="icon wb-search" aria-hidden="true"></i>
      </button>
    </div>

    <div class="navbar-container container-fluid">
      <!-- Navbar Collapse -->
      <div class="collapse navbar-collapse navbar-collapse-toolbar" id="site-navbar-collapse">
        <!-- Navbar Toolbar -->
        <ul class="nav navbar-toolbar">
          <li class="nav-item hidden-float" id="toggleMenubar">
            <a class="nav-link" data-toggle="menubar" href="#" role="button">
                <i class="icon hamburger hamburger-arrow-left">
                  <span class="sr-only">Toggle menubar</span>
                  <span class="hamburger-bar"></span>
                </i>
              </a>
          </li>
          <li class="nav-item hidden-sm-down" id="toggleFullscreen">
            <a class="nav-link icon icon-fullscreen" data-toggle="fullscreen" href="#" role="button">
              <span class="sr-only">Toggle fullscreen</span>
            </a>
          </li>
          <li class="nav-item hidden-float">
            <a class="nav-link icon wb-search" data-toggle="collapse" href="#" data-target="#site-navbar-search"
              role="button">
              <span class="sr-only">Toggle Search</span>
            </a>
          </li>
          <li class="nav-item dropdown dropdown-fw dropdown-mega">
            <a class="nav-link" data-toggle="dropdown" href="#" aria-expanded="false" data-animation="fade"
              role="button">Mega <i class="icon wb-chevron-down-mini" aria-hidden="true"></i></a>
            <div class="dropdown-menu" role="menu">
              <div class="mega-content">
                <div class="row">
                  <div class="col-md-4">
                    <h5>UI Kit</h5>
                    <ul class="blocks-2">
                      <li class="mega-menu m-0">
                        <ul class="list-icons">
                          <li><i class="wb-chevron-right-mini" aria-hidden="true"></i>
                            <a
                              href="advanced/animation.html">Animation</a>
                          </li>
                          <li><i class="wb-chevron-right-mini" aria-hidden="true"></i>
                            <a
                              href="uikit/buttons.html">Buttons</a>
                          </li>
                          <li><i class="wb-chevron-right-mini" aria-hidden="true"></i>
                            <a
                              href="uikit/colors.html">Colors</a>
                          </li>
                          <li><i class="wb-chevron-right-mini" aria-hidden="true"></i>
                            <a
                              href="uikit/dropdowns.html">Dropdowns</a>
                          </li>
                          <li><i class="wb-chevron-right-mini" aria-hidden="true"></i>
                            <a
                              href="uikit/icons.html">Icons</a>
                          </li>
                          <li><i class="wb-chevron-right-mini" aria-hidden="true"></i>
                            <a
                              href="advanced/lightbox.html">Lightbox</a>
                          </li>
                        </ul>
                      </li>
                      <li class="mega-menu m-0">
                        <ul class="list-icons">
                          <li><i class="wb-chevron-right-mini" aria-hidden="true"></i>
                            <a
                              href="uikit/modals.html">Modals</a>
                          </li>
                          <li><i class="wb-chevron-right-mini" aria-hidden="true"></i>
                            <a
                              href="uikit/panel-structure.html">Panels</a>
                          </li>
                          <li><i class="wb-chevron-right-mini" aria-hidden="true"></i>
                            <a
                              href="structure/overlay.html">Overlay</a>
                          </li>
                          <li><i class="wb-chevron-right-mini" aria-hidden="true"></i>
                            <a
                              href="uikit/tooltip-popover.html">Tooltips</a>
                          </li>
                          <li><i class="wb-chevron-right-mini" aria-hidden="true"></i>
                            <a
                              href="advanced/scrollable.html">Scrollable</a>
                          </li>
                          <li><i class="wb-chevron-right-mini" aria-hidden="true"></i>
                            <a
                              href="uikit/typography.html">Typography</a>
                          </li>
                        </ul>
                      </li>
                    </ul>
                  </div>
                  <div class="col-md-4">
                    <h5>Media
                      <span class="badge badge-pill badge-success">4</span>
                    </h5>
                    <ul class="blocks-3">
                      <li>
                        <a class="thumbnail m-0" href="javascript:void(0)">
                          <img class="w-full" src="${url}global/photos/view-1-150x100.jpg" alt="..." />
                        </a>
                      </li>
                      <li>
                        <a class="thumbnail m-0" href="javascript:void(0)">
                          <img class="w-full" src="${url}global/photos/view-2-150x100.jpg" alt="..." />
                        </a>
                      </li>
                      <li>
                        <a class="thumbnail m-0" href="javascript:void(0)">
                          <img class="w-full" src="${url}global/photos/view-3-150x100.jpg" alt="..." />
                        </a>
                      </li>
                      <li>
                        <a class="thumbnail m-0" href="javascript:void(0)">
                          <img class="w-full" src="${url}global/photos/view-4-150x100.jpg" alt="..." />
                        </a>
                      </li>
                      <li>
                        <a class="thumbnail m-0" href="javascript:void(0)">
                          <img class="w-full" src="${url}global/photos/view-5-150x100.jpg" alt="..." />
                        </a>
                      </li>
                      <li>
                        <a class="thumbnail m-0" href="javascript:void(0)">
                          <img class="w-full" src="${url}global/photos/view-6-150x100.jpg" alt="..." />
                        </a>
                      </li>
                    </ul>
                  </div>
                  <div class="col-md-4">
                    <h5 class="mb-0">Accordion</h5>
                    <!-- Accordion -->
                    <div class="panel-group panel-group-simple" id="siteMegaAccordion" aria-multiselectable="true"
                      role="tablist">
                      <div class="panel">
                        <div class="panel-heading" id="siteMegaAccordionHeadingOne" role="tab">
                          <a class="panel-title" data-toggle="collapse" href="#siteMegaCollapseOne" data-parent="#siteMegaAccordion"
                            aria-expanded="false" aria-controls="siteMegaCollapseOne">
                              Collapsible Group Item #1
                            </a>
                        </div>
                        <div class="panel-collapse collapse" id="siteMegaCollapseOne" aria-labelledby="siteMegaAccordionHeadingOne"
                          role="tabpanel">
                          <div class="panel-body">
                            De moveat laudatur vestra parum doloribus labitur sentire partes, eripuit praesenti
                            congressus ostendit alienae, voluptati ornateque accusamus
                            clamat reperietur convicia albucius.
                          </div>
                        </div>
                      </div>
                      <div class="panel">
                        <div class="panel-heading" id="siteMegaAccordionHeadingTwo" role="tab">
                          <a class="panel-title collapsed" data-toggle="collapse" href="#siteMegaCollapseTwo"
                            data-parent="#siteMegaAccordion" aria-expanded="false"
                            aria-controls="siteMegaCollapseTwo">
                              Collapsible Group Item #2
                            </a>
                        </div>
                        <div class="panel-collapse collapse" id="siteMegaCollapseTwo" aria-labelledby="siteMegaAccordionHeadingTwo"
                          role="tabpanel">
                          <div class="panel-body">
                            Praestabiliorem. Pellat excruciant legantur ullum leniter vacare foris voluptate
                            loco ignavi, credo videretur multoque choro fatemur
                            mortis animus adoptionem, bello statuat expediunt naturales.
                          </div>
                        </div>
                      </div>

                      <div class="panel">
                        <div class="panel-heading" id="siteMegaAccordionHeadingThree" role="tab">
                          <a class="panel-title collapsed" data-toggle="collapse" href="#siteMegaCollapseThree"
                            data-parent="#siteMegaAccordion" aria-expanded="false"
                            aria-controls="siteMegaCollapseThree">
                              Collapsible Group Item #3
                            </a>
                        </div>
                        <div class="panel-collapse collapse" id="siteMegaCollapseThree" aria-labelledby="siteMegaAccordionHeadingThree"
                          role="tabpanel">
                          <div class="panel-body">
                            Horum, antiquitate perciperet d conspectum locus obruamus animumque perspici probabis
                            suscipere. Desiderat magnum, contenta poena desiderant
                            concederetur menandri damna disputandum corporum.
                          </div>
                        </div>
                      </div>
                    </div>
                    <!-- End Accordion -->
                  </div>
                </div>
              </div>
            </div>
          </li>
        </ul>
        <!-- End Navbar Toolbar -->

        <!-- Navbar Toolbar Right -->
        <ul class="nav navbar-toolbar navbar-right navbar-toolbar-right">
          <li class="nav-item dropdown">
            <a class="nav-link" data-toggle="dropdown" href="javascript:void(0)" data-animation="scale-up"
              aria-expanded="false" role="button">
              <span class="flag-icon flag-icon-us"></span>
            </a>
            <div class="dropdown-menu" role="menu">
              <a class="dropdown-item" href="javascript:void(0)" role="menuitem">
                <span class="flag-icon flag-icon-gb"></span> English</a>
              <a class="dropdown-item" href="javascript:void(0)" role="menuitem">
                <span class="flag-icon flag-icon-fr"></span> French</a>
              <a class="dropdown-item" href="javascript:void(0)" role="menuitem">
                <span class="flag-icon flag-icon-cn"></span> Chinese</a>
              <a class="dropdown-item" href="javascript:void(0)" role="menuitem">
                <span class="flag-icon flag-icon-de"></span> German</a>
              <a class="dropdown-item" href="javascript:void(0)" role="menuitem">
                <span class="flag-icon flag-icon-nl"></span> Dutch</a>
            </div>
          </li>
          <li class="nav-item dropdown">
            <a class="nav-link navbar-avatar" data-toggle="dropdown" href="#" aria-expanded="false"
              data-animation="scale-up" role="button">
              <span class="avatar avatar-online">
                <img src="${url}global/portraits/5.jpg" alt="...">
                <i></i>
              </span>
            </a>
            <div class="dropdown-menu" role="menu">
              <a class="dropdown-item" href="javascript:void(0)" role="menuitem"><i class="icon wb-user" aria-hidden="true"></i> Profile</a>
              <a class="dropdown-item" href="javascript:void(0)" role="menuitem"><i class="icon wb-payment" aria-hidden="true"></i> Billing</a>
              <a class="dropdown-item" href="javascript:void(0)" role="menuitem"><i class="icon wb-settings" aria-hidden="true"></i> Settings</a>
              <div class="dropdown-divider" role="presentation"></div>
              <a class="dropdown-item" href="javascript:void(0)" role="menuitem"><i class="icon wb-power" aria-hidden="true"></i> Logout</a>
            </div>
          </li>
          <li class="nav-item dropdown">
            <a class="nav-link" data-toggle="dropdown" href="javascript:void(0)" title="Notifications"
              aria-expanded="false" data-animation="scale-up" role="button">
                <i class="icon wb-bell" aria-hidden="true"></i>
                <span class="badge badge-pill badge-danger up">5</span>
              </a>
            <div class="dropdown-menu dropdown-menu-right dropdown-menu-media" role="menu">
              <div class="dropdown-menu-header">
                <h5>NOTIFICATIONS</h5>
                <span class="badge badge-round badge-danger">New 5</span>
              </div>

              <div class="list-group">
                <div data-role="container">
                  <div data-role="content">
                    <a class="list-group-item dropdown-item" href="javascript:void(0)" role="menuitem">
                      <div class="media">
                        <div class="pr-10">
                          <i class="icon wb-order bg-red-600 white icon-circle" aria-hidden="true"></i>
                        </div>
                        <div class="media-body">
                          <h6 class="media-heading">A new order has been placed</h6>
                          <time class="media-meta" datetime="2018-06-12T20:50:48+08:00">5 hours ago</time>
                        </div>
                      </div>
                    </a>
                    <a class="list-group-item dropdown-item" href="javascript:void(0)" role="menuitem">
                      <div class="media">
                        <div class="pr-10">
                          <i class="icon wb-user bg-green-600 white icon-circle" aria-hidden="true"></i>
                        </div>
                        <div class="media-body">
                          <h6 class="media-heading">Completed the task</h6>
                          <time class="media-meta" datetime="2018-06-11T18:29:20+08:00">2 days ago</time>
                        </div>
                      </div>
                    </a>
                    <a class="list-group-item dropdown-item" href="javascript:void(0)" role="menuitem">
                      <div class="media">
                        <div class="pr-10">
                          <i class="icon wb-settings bg-red-600 white icon-circle" aria-hidden="true"></i>
                        </div>
                        <div class="media-body">
                          <h6 class="media-heading">Settings updated</h6>
                          <time class="media-meta" datetime="2018-06-11T14:05:00+08:00">2 days ago</time>
                        </div>
                      </div>
                    </a>
                    <a class="list-group-item dropdown-item" href="javascript:void(0)" role="menuitem">
                      <div class="media">
                        <div class="pr-10">
                          <i class="icon wb-calendar bg-blue-600 white icon-circle" aria-hidden="true"></i>
                        </div>
                        <div class="media-body">
                          <h6 class="media-heading">Event started</h6>
                          <time class="media-meta" datetime="2018-06-10T13:50:18+08:00">3 days ago</time>
                        </div>
                      </div>
                    </a>
                    <a class="list-group-item dropdown-item" href="javascript:void(0)" role="menuitem">
                      <div class="media">
                        <div class="pr-10">
                          <i class="icon wb-chat bg-orange-600 white icon-circle" aria-hidden="true"></i>
                        </div>
                        <div class="media-body">
                          <h6 class="media-heading">Message received</h6>
                          <time class="media-meta" datetime="2018-06-10T12:34:48+08:00">3 days ago</time>
                        </div>
                      </div>
                    </a>
                  </div>
                </div>
              </div>
              <div class="dropdown-menu-footer">
                <a class="dropdown-menu-footer-btn" href="javascript:void(0)" role="button">
                    <i class="icon wb-settings" aria-hidden="true"></i>
                  </a>
                <a class="dropdown-item" href="javascript:void(0)" role="menuitem">
                    All notifications
                  </a>
              </div>
            </div>
          </li>
          <li class="nav-item dropdown">
            <a class="nav-link" data-toggle="dropdown" href="javascript:void(0)" title="Messages"
              aria-expanded="false" data-animation="scale-up" role="button">
                <i class="icon wb-envelope" aria-hidden="true"></i>
                <span class="badge badge-pill badge-info up">3</span>
              </a>
            <div class="dropdown-menu dropdown-menu-right dropdown-menu-media" role="menu">
              <div class="dropdown-menu-header" role="presentation">
                <h5>MESSAGES</h5>
                <span class="badge badge-round badge-info">New 3</span>
              </div>

              <div class="list-group" role="presentation">
                <div data-role="container">
                  <div data-role="content">
                    <a class="list-group-item" href="javascript:void(0)" role="menuitem">
                      <div class="media">
                        <div class="pr-10">
                          <span class="avatar avatar-sm avatar-online">
                            <img src="${url}global/portraits/2.jpg" alt="..." />
                            <i></i>
                          </span>
                        </div>
                        <div class="media-body">
                          <h6 class="media-heading">Mary Adams</h6>
                          <div class="media-meta">
                            <time datetime="2018-06-17T20:22:05+08:00">30 minutes ago</time>
                          </div>
                          <div class="media-detail">Anyways, i would like just do it</div>
                        </div>
                      </div>
                    </a>
                    <a class="list-group-item" href="javascript:void(0)" role="menuitem">
                      <div class="media">
                        <div class="pr-10">
                          <span class="avatar avatar-sm avatar-off">
                            <img src="${url}global/portraits/3.jpg" alt="..." />
                            <i></i>
                          </span>
                        </div>
                        <div class="media-body">
                          <h6 class="media-heading">Caleb Richards</h6>
                          <div class="media-meta">
                            <time datetime="2018-06-17T12:30:30+08:00">12 hours ago</time>
                          </div>
                          <div class="media-detail">I checheck the document. But there seems</div>
                        </div>
                      </div>
                    </a>
                    <a class="list-group-item" href="javascript:void(0)" role="menuitem">
                      <div class="media">
                        <div class="pr-10">
                          <span class="avatar avatar-sm avatar-busy">
                            <img src="${url}global/portraits/4.jpg" alt="..." />
                            <i></i>
                          </span>
                        </div>
                        <div class="media-body">
                          <h6 class="media-heading">June Lane</h6>
                          <div class="media-meta">
                            <time datetime="2018-06-16T18:38:40+08:00">2 days ago</time>
                          </div>
                          <div class="media-detail">Lorem ipsum Id consectetur et minim</div>
                        </div>
                      </div>
                    </a>
                    <a class="list-group-item" href="javascript:void(0)" role="menuitem">
                      <div class="media">
                        <div class="pr-10">
                          <span class="avatar avatar-sm avatar-away">
                            <img src="${url}global/portraits/5.jpg" alt="..." />
                            <i></i>
                          </span>
                        </div>
                        <div class="media-body">
                          <h6 class="media-heading">Edward Fletcher</h6>
                          <div class="media-meta">
                            <time datetime="2018-06-15T20:34:48+08:00">3 days ago</time>
                          </div>
                          <div class="media-detail">Dolor et irure cupidatat commodo nostrud nostrud.</div>
                        </div>
                      </div>
                    </a>
                  </div>
                </div>
              </div>
              <div class="dropdown-menu-footer" role="presentation">
                <a class="dropdown-menu-footer-btn" href="javascript:void(0)" role="button">
                    <i class="icon wb-settings" aria-hidden="true"></i>
                  </a>
                <a class="dropdown-item" href="javascript:void(0)" role="menuitem">
                    See all messages
                  </a>
              </div>
            </div>
          </li>
        </ul>
        <!-- End Navbar Toolbar Right -->
      </div>
      <!-- End Navbar Collapse -->

      <!-- Site Navbar Seach -->
      <div class="collapse navbar-search-overlap" id="site-navbar-search">
        <form role="search">
          <div class="form-group">
            <div class="input-search">
              <i class="input-search-icon wb-search" aria-hidden="true"></i>
              <input type="text" class="form-control" name="site-search" placeholder="Search...">
              <button type="button" class="input-search-close icon wb-close" data-target="#site-navbar-search"
                data-toggle="collapse" aria-label="Close"></button>
            </div>
          </div>
        </form>
      </div>
      <!-- End Site Navbar Seach -->
    </div>
  </nav>
  <div class="site-menubar">
    <div class="site-menubar-body">
      <div>
        <div>
          <ul class="site-menu" data-plugin="menu">
            <li class="site-menu-category">General</li>
            <li class="site-menu-item has-sub">
              <a href="javascript:void(0)">
                  <i class="site-menu-icon wb-dashboard" aria-hidden="true"></i>
                  <span class="site-menu-title">Dashboard</span>
                      <div class="site-menu-badge">
                          <span class="badge badge-pill badge-success">3</span>
                      </div>
              </a>
              <ul class="site-menu-sub">
                <li class="site-menu-item active">
                  <a href="index.html">
                    <span class="site-menu-title">Dashboard v1</span>
                  </a>
                </li>
              </ul>
            </li>
            <li class="site-menu-item has-sub">
              <a href="javascript:void(0)">
                   <i class="site-menu-icon wb-layout" aria-hidden="true"></i>
                   <span class="site-menu-title">School</span>
                           <span class="site-menu-arrow"></span>
               </a>
              <ul class="site-menu-sub">
                <li class="site-menu-item">
                  <a href="${contextPathURl}/School/addSchool">
                    <span class="site-menu-title">Add School</span>
                  </a>
                </li>
                <li class="site-menu-item">
                  <a href="${contextPathURl}/School/SchoolList">
                    <span class="site-menu-title">School List</span>
                  </a>
                </li>
              </ul>
            </li>
            
            <li class="site-menu-category">Ticket</li>
            <li class="site-menu-item has-sub">
              <a href="${contextPathURl}/Ticket/ticketCategory">
                  <i class="site-menu-icon wb-file" aria-hidden="true"></i>
                  <span class="site-menu-title">Ticket Category</span>
              </a>
            </li>
            <li class="site-menu-item has-sub">
              <a href="${contextPathURl}/Ticket/AddTicket">
                  <i class="site-menu-icon wb-clipboard" aria-hidden="true"></i>
                  <span class="site-menu-title">Generate Ticket</span>
              </a>
            </li>
            <li class="site-menu-item has-sub">
              <a href="${contextPathURl}/Ticket/ticketList">
                  <i class="site-menu-icon wb-align-justify" aria-hidden="true"></i>
                  <span class="site-menu-title">Ticket History</span>
              </a>
            </li>
           
           
            <li class="site-menu-category">Master Data</li>
            <li class="site-menu-item has-sub">
              <a href="javascript:void(0)">
                   <i class="site-menu-icon wb-map" aria-hidden="true"></i>
                   <span class="site-menu-title">Region</span>
                           <span class="site-menu-arrow"></span>
               </a>
              <ul class="site-menu-sub">
                <li class="site-menu-item">
                  <a href="${contextPathURl}/Region/State">
                    <span class="site-menu-title">State</span>
                  </a>
                </li>
                <li class="site-menu-item">
                  <a href="${contextPathURl}/Region/District">
                    <span class="site-menu-title">District</span>
                  </a>
                </li>
                    <li class="site-menu-item">
                  <a href="${contextPathURl}/Region/Taluk">
                    <span class="site-menu-title">Taluk</span>
                  </a>
                </li>
                <li class="site-menu-item">
                  <a href="${contextPathURl}/Region/Hobli">
                    <span class="site-menu-title">Hobli</span>
                  </a>
                </li>
                <li class="site-menu-item">
                  <a href="${contextPathURl}/Region/Village">
                    <span class="site-menu-title">Village</span>
                  </a>
                </li>
              </ul>
            </li>
            <li class="site-menu-item has-sub">
              <a href="javascript:void(0)">
                   <i class="site-menu-icon wb-extension" aria-hidden="true"></i>
                   <span class="site-menu-title">Cluster</span>
                           <span class="site-menu-arrow"></span>
               </a>
              <ul class="site-menu-sub">
                <li class="site-menu-item">
                  <a href="${contextPathURl}/Cluster/addCluster">
                    <span class="site-menu-title">Add Cluster</span>
                  </a>
                </li>
                <li class="site-menu-item">
                  <a href="${contextPathURl}/Cluster/ClusterList">
                    <span class="site-menu-title">Cluster List</span>
                  </a>
                </li>
              </ul>
            </li>
             <li class="site-menu-item has-sub">
              <a href="javascript:void(0)">
                   <i class="site-menu-icon wb-user" aria-hidden="true"></i> 
                   <span class="site-menu-title">Donor</span>
                           <span class="site-menu-arrow"></span>
               </a>
              <ul class="site-menu-sub">
                <li class="site-menu-item">
                  <a href="${contextPathURl}/Donor/addDonor">
                    <span class="site-menu-title">Add Donor</span>
                  </a>
                </li>
                <li class="site-menu-item">
                  <a href="${contextPathURl}/Donor/DonorList">
                    <span class="site-menu-title">Donor List</span>
                  </a>
                </li>
              </ul>
            </li>
          </ul>
        </div>
      </div>
    </div>

    <div class="site-menubar-footer">
      <a href="javascript: void(0);" class="fold-show" data-placement="top" data-toggle="tooltip"
        data-original-title="Settings">
        <span class="icon wb-settings" aria-hidden="true"></span>
      </a>
      <a href="javascript: void(0);" data-placement="top" data-toggle="tooltip" data-original-title="Lock">
        <span class="icon wb-eye-close" aria-hidden="true"></span>
      </a>
      <a href="javascript: void(0);" data-placement="top" data-toggle="tooltip" data-original-title="Logout">
        <span class="icon wb-power" aria-hidden="true"></span>
      </a>
    </div>
  </div>
  
  <div class="site-gridmenu">
    <div>
      <div>
        <ul>
          <li>
            <a href="apps/mailbox/mailbox.html">
                <i class="icon wb-envelope"></i>
                <span>Mailbox</span>
              </a>
          </li>
          <li>
            <a href="apps/calendar/calendar.html">
                <i class="icon wb-calendar"></i>
                <span>Calendar</span>
              </a>
          </li>
          <li>
            <a href="apps/contacts/contacts.html">
                <i class="icon wb-user"></i>
                <span>Contacts</span>
              </a>
          </li>
          <li>
            <a href="apps/media/overview.html">
                <i class="icon wb-camera"></i>
                <span>Media</span>
              </a>
          </li>
          <li>
            <a href="apps/documents/categories.html">
                <i class="icon wb-order"></i>
                <span>Documents</span>
              </a>
          </li>
          <li>
            <a href="apps/projects/projects.html">
                <i class="icon wb-image"></i>
                <span>Project</span>
              </a>
          </li>
          <li>
            <a href="apps/forum/forum.html">
                <i class="icon wb-chat-group"></i>
                <span>Forum</span>
              </a>
          </li>
          <li>
            <a href="index.html">
                <i class="icon wb-dashboard"></i>
                <span>Dashboard</span>
              </a>
          </li>
        </ul>
      </div>
    </div>
  </div>