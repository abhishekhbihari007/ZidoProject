/** @type {import('@vercel/node').VercelConfig} */
module.exports = {
  version: 2,
  builds: [
    {
      src: 'client/package.json',
      use: '@vercel/static-build',
      config: {
        distDir: 'client/dist'
      }
    }
  ],
  routes: [
    {
      src: '/(.*)',
      dest: '/client/index.html'
    }
  ],
  env: {
    NODE_ENV: 'production'
  },
  functions: {
    'client/dist/**/*.js': {
      runtime: 'nodejs18.x'
    }
  },
  // Custom headers for better performance
  headers: [
    {
      source: '/(.*)',
      headers: [
        {
          key: 'X-Content-Type-Options',
          value: 'nosniff'
        },
        {
          key: 'X-Frame-Options',
          value: 'DENY'
        },
        {
          key: 'X-XSS-Protection',
          value: '1; mode=block'
        }
      ]
    },
    {
      source: '/assets/(.*)',
      headers: [
        {
          key: 'Cache-Control',
          value: 'public, max-age=31536000, immutable'
        }
      ]
    }
  ],
  // Redirects for better SEO
  redirects: [
    {
      source: '/home',
      destination: '/',
      permanent: true
    }
  ]
};
